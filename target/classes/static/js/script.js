document.getElementById('loadFileButton').addEventListener('click', () => {
    const input = document.createElement('input');
    input.type = 'file';
    input.accept = '.csv';

    input.addEventListener('change', (event) => {
        const file = event.target.files[0];
        const formData = new FormData();
        formData.append('file', file);

        fetch('/longest-pair-common-projects',
            {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                if (data.length === 0) {
                    document.getElementById('result').textContent = 'No available data!';
                } else {
                    displayDataInGrid(data); // New function to display data
                }
            })
            .catch(error => {
                console.error('Error:', error);
                document.getElementById('result').textContent = 'Error uploading file.';
            });
    });

    input.click();
});

function displayDataInGrid(data) {

    // Create the table element
    const table = document.createElement('table');
    table.classList.add('data-grid');

    // Create table header row
    const headerRow = document.createElement('tr');
    headerRow.innerHTML = `<th>Employee ID #1</th><th>Employee ID #2</th><th>Project ID</th><th>Days Worked</th>`;
    table.appendChild(headerRow);

    // Create table body rows
    data.forEach(pair => {
        const row = document.createElement('tr');
        row.innerHTML = `<td>${pair.firstEmployeeId}</td>
                          <td>${pair.secondEmployeeId}</td>
                          <td>${pair.projectId}</td>
                          <td>${pair.daysWorkedTogether}</td>`;
        table.appendChild(row);
    });

    // Display the table in the result div
    document.getElementById('result').innerHTML = ''; // Clear existing content
    document.getElementById('result').appendChild(table);
}