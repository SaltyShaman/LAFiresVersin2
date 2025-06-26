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

        // Handle edit - redirect to update-siren.html with sirenId query param
        document.querySelectorAll('.edit-btn').forEach(button => {
            button.addEventListener('click', (e) => {
                const id = e.target.dataset.id;
                console.log("Edit siren with ID:", id);
                window.location.href = `update-siren.html?sirenId=${id}`;
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
            alert(`Sirene with ID ${id} was successfully deleted.`);
            fetchSirens(); // Refresh table
        } else {
            const errorData = await response.json(); // Læs JSON med fejlbesked
            alert(`Sletning mislykkedes: ${errorData.message}`);
            console.error("Fejl:", errorData);
        }
    } catch (err) {
        console.error("Delete error:", err);
        alert("Uventet fejl ved sletning.");
    }
}


fetchSirens();
