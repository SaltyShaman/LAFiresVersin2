console.log("Create Siren API er hentet");

document.getElementById('createSirenForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const data = {
        latitude: parseFloat(document.getElementById('latitude').value),
        longitude: parseFloat(document.getElementById('longitude').value),
        status: document.getElementById('status').value,
        fireIds: []
    };

    try {
        const response = await fetch('http://localhost:8080/sirens', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            const created = await response.json();
            document.getElementById('message').textContent = 'Sirene created with ID: ' + created.sireneId;
            this.reset();
        } else {
            document.getElementById('message').textContent = 'Error creating sirene: ' + response.statusText;
        }
    } catch (error) {
        document.getElementById('message').textContent = 'Error: ' + error.message;
    }
});