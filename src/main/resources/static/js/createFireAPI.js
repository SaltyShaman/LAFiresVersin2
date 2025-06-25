
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("fireForm");
    const responseMessage = document.getElementById("responseMessage");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const fireData = {
            latitude: parseFloat(document.getElementById("latitude").value),
            longitude: parseFloat(document.getElementById("longitude").value),
            closed: false
        };

        fetch("http://localhost:8080/fires", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(fireData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Fejl ved oprettelse af brand");
                }
                return response.json();
            })
            .then(data => {
                responseMessage.textContent = `Brand oprettet med ID: ${data.fireId}`;
                form.reset();
            })
            .catch(error => {
                responseMessage.textContent = `Fejl: ${error.message}`;
            });
    });
});