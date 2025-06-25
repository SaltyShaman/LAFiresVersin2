console.log("Get All Sirens API loaded");

async function fetchSirens() {
    try {
        const response = await fetch('http://localhost:8080/sirens');
        const sirens = await response.json();

        const tableBody = document.getElementById('tableBody');
        tableBody.innerHTML = '';

        sirens.forEach(siren => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${siren.sireneId}</td>
                <td>${siren.latitude}</td>
                <td>${siren.longitude}</td>
                <td>${siren.status}</td>
                <td><button class="edit-btn" data-id="${siren.sireneId}">Edit</button></td>
                <td><button class="delete-btn" data-id="${siren.sireneId}">Delete</button></td>
            `;
            tableBody.appendChild(row);
        });

        // Handle delete
        document.querySelectorAll('.delete-btn').forEach(button => {
            button.addEventListener('click', async (e) => {
                const id = e.target.dataset.id;
                await deleteSiren(id);
            });
        });

        // Handle edit
        document.querySelectorAll('.edit-btn').forEach(button => {
            button.addEventListener('click', (e) => {
                const id = e.target.dataset.id;
                console.log("Edit siren with ID:", id);
                // You can open a modal or redirect to an edit form here
            });
        });

    } catch (err) {
        console.error("Error fetching sirens:", err);
    }
}

async function deleteSiren(id) {
    try {
        const response = await fetch(`http://localhost:8080/sirens/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            fetchSirens(); // Refresh table
        } else {
            console.error("Failed to delete siren with ID:", id);
        }
    } catch (err) {
        console.error("Delete error:", err);
    }
}

fetchSirens();
