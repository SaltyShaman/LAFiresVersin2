document.addEventListener('DOMContentLoaded', async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const sirenId = urlParams.get('sirenId');

    if (!sirenId) {
        alert("No siren ID specified!");
        return;
    }

    const sireneIdSpan = document.getElementById('sireneId');
    const latitudeInput = document.getElementById('latitude');
    const longitudeInput = document.getElementById('longitude');
    const statusInput = document.getElementById('status');
    const form = document.getElementById('updateSireneForm');
    const deleteBtn = document.getElementById('deleteBtn');

    // Vis sirene-id
    sireneIdSpan.textContent = sirenId;

    try {
        const res = await fetch(`http://localhost:8080/sirens`);
        const sirens = await res.json();
        const siren = sirens.find(s => s.sireneId == sirenId);

        if (!siren) {
            alert("Siren not found!");
            return;
        }

        latitudeInput.value = siren.latitude;
        longitudeInput.value = siren.longitude;
        statusInput.value = siren.status;

    } catch (err) {
        alert("Failed to load siren data.");
        console.error(err);
        return;
    }

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const updatedSirene = {
            latitude: parseFloat(latitudeInput.value),
            longitude: parseFloat(longitudeInput.value),
            status: statusInput.value
        };

        try {
            const response = await fetch(`http://localhost:8080/sirens/${sirenId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updatedSirene)
            });

            if (response.ok) {
                alert("Siren updated successfully!");
                window.location.href = "see-all-sirens.html";
            } else {
                const errorData = await response.json();
                alert("Opdatering mislykkedes: " + (errorData.message || "Ukendt fejl"));
            }
        } catch (err) {
            alert("Error updating siren.");
            console.error(err);
        }
    });

    deleteBtn.addEventListener('click', async () => {
        const confirmDelete = confirm("Er du sikker på, at du vil slette denne sirene?");
        if (!confirmDelete) return;

        try {
            const res = await fetch(`http://localhost:8080/sirens/${sirenId}`, {
                method: 'DELETE'
            });

            if (res.ok) {
                alert("Sirene slettet.");
                window.location.href = "see-all-sirens.html";
            } else {
                const errorData = await res.json();
                alert("Sletning mislykkedes: " + (errorData.message || "Ukendt fejl"));
            }
        } catch (err) {
            alert("Fejl ved sletning.");
            console.error(err);
        }
    });
});

