document.addEventListener("DOMContentLoaded", function () {
    fetch('/admin-info')
        .then(response => response.json())
        .then(data => {
            const user = data;
            if(!user) {
                throw new Error('User not found')
            }
            const tableBody = document.querySelector('#user-table-body');


            const row = document.createElement('tr');
            row.innerHTML = `<td>${user.id}</td>
                                 <td>${user.age}</td>
                                 <td>${user.email}</td>
                                 <td>${user.firstName}</td>
                                 <td>${user.lastName}</td>
                                 <td>
                                    <ul>${user.roles.map(role => `<li>${role.roleName}</i>`).join('')}
                                    </ul>
                                 </td>`;
            tableBody.appendChild(row);

        })
        .catch(error => console.error('User not found', error));

});