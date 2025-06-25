console.log("navigation fil er hentet");

document.addEventListener("DOMContentLoaded", function () {
    const buttonActions = {
        "firesBtn": "active-fires.html",
        "sirensBtn": "see-all-sirens.html",
        "indexBtn": "index.html",
        "createSirensBtn": "create-siren.html",
        "registerFiresBtn": "register-fire.html"
        // edit-btn håndteres separat, da det er knapper genereret dynamisk
    };

    // Bind klik til faste knapper med id
    Object.keys(buttonActions).forEach(id => {
        const button = document.getElementById(id);
        if (button) {
            button.addEventListener("click", () => window.location.href = buttonActions[id]);
        }
    });

    // Naviger når en rediger-knap trykkes (knapper med klasse 'edit-btn')
    document.querySelectorAll(".edit-btn").forEach(button => {
        button.addEventListener("click", function () {
            const sirenId = this.getAttribute("data-sirenid");
            if (sirenId) {
                // Naviger til update-siren.html med sirenId som query parameter
                window.location.href = `update-siren.html?sirenId=${sirenId}`;
            } else {
                alert("Sirene ID mangler!");
            }
        });
    });
});