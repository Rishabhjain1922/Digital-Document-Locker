// Show Upload Section
function showUploadSection() {
    document.getElementById("upload-section").scrollIntoView({ behavior: "smooth" });
}

// Upload Document
document.getElementById("uploadForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const file = document.getElementById("file").files[0];
    const password = document.getElementById("password").value;

    const formData = new FormData();
    formData.append("file", file);
    formData.append("password", password);

    const response = await fetch("/documents/upload", {
        method: "POST",
        body: formData,
    });

    if (response.ok) {
        const result = await response.json();
        alert(`File uploaded successfully! Document Key: ${result.key}`);
    } else {
        alert("File upload failed! Please try again.");
    }
});

// Retrieve Document
document.getElementById("retrieveForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const key = document.getElementById("key").value;
    const password = document.getElementById("retrievePassword").value;

    const response = await fetch(`/documents/${key}?password=${password}`, {
        method: "GET",
    });

    if (response.ok) {
        const blob = await response.blob();
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement("a");
        link.href = url;
        link.download = "document";
        link.click();
        alert("Document retrieved successfully!");
    } else {
        alert("Failed to retrieve document! Check your key and password.");
    }
});
