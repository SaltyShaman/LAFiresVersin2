document.addEventListener("DOMContentLoaded", () => {
    loadFires();
});

function loadFires() {
    fetch("http://localhost:8080/fires/active")
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById("fireTableBody");
            tbody.innerHTML = ""; // ryd tidligere indhold

            if (data.length === 0) {
                // Hvis ingen aktive brande, indsæt en besked i tabellen
                const row = document.createElement("tr");
                row.innerHTML = `<td colspan="6" style="text-align:center; font-style: italic;">Ingen aktive brande</td>`;
                tbody.appendChild(row);
                return;
            }

            data.forEach(fire => {
                const row = document.createElement("tr");

                const sireneListe = fire.sirenes.length > 0
                    ? fire.sirenes.map(s => `#${s.sireneId}`).join(", ")
                    : "Ingen";

                row.innerHTML = `
                    <td>${fire.fireId}</td>
                    <td>${fire.latitude}</td>
                    <td>${fire.longitude}</td>
                    <td>${new Date(fire.timestamp).toLocaleString()}</td>
                    <td>${sireneListe}</td>
                    <td>
                        <button onclick="closeFire(${fire.fireId})">Luk brand</button>
                    </td>
                `;

                tbody.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Fejl ved hentning af brande:", error);
        });
}


function closeFire(fireId) {
    fetch(`http://localhost:8080/fires/${fireId}/close`, {
        method: "PATCH"
    })
        .then(response => {
            if (response.ok) {
                alert(`Brand #${fireId} blev lukket.`);
                loadFires(); // genindlæs listen
            } else {
                alert(`Kunne ikke lukke brand #${fireId}.`);
            }
        })
        .catch(error => {
            console.error("Fejl ved lukning:", error);
            alert("Netværksfejl.");
        });
}
