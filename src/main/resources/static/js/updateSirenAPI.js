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

    // Show ID
    sireneIdSpan.textContent = sirenId;

    try {
        // Fetch siren data
        const res = await fetch(`http://localhost:8080/sirens`);
        const sirens = await res.json();
        const siren = sirens.find(s => s.sireneId == sirenId);

        if (!siren) {
            alert("Siren not found!");
            return;
        }

        // Populate form fields
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
                // Optionally redirect back to siren list
                window.location.href = "see-all-sirens.html";
            } else {
                alert("Failed to update siren.");
            }
        } catch (err) {
            alert("Error updating siren.");
            console.error(err);
        }
    });
});
