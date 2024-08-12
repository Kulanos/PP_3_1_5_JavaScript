document.addEventListener("DOMContentLoaded", function () {
    fetch('/admin')
        .then(respons => respons.json())
        .then(data => {
            const tableBody = document.querySelector('#user-table-body');
            data.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `<td>${user.id}</td>
                                <td>${user.age}</td>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>
                                    <ul>${user.roles.map(role => `<li>${role.roleName}</i>`).join('')}
                                    </ul>
                                </td>
                                <td><button class="btn btn-danger btn-sm delete-btn" data-id="${user.id}">Delete</button>
                                    <button class="btn btn-primary btn-sm update-btn" data-id="${user.id}">Update</button>
                                </td>`;
                tableBody.appendChild(row)
            });
            document.querySelectorAll('.delete-btn').forEach(button => {
                button.addEventListener('click', function () {
                    const  userId = this.getAttribute('data-id');
                    fetch(`/admin/delete-user/${userId}`, {method: 'DELETE'})
                        .then(response => {
                            if(response.ok) {
                                this.closest('tr').remove();
                            } else {
                                console.error('Failed to delete user');
                            }
                        });

                });
            });

            document.querySelectorAll('.update-btn').forEach(button => {
                button.addEventListener('click', function () {
                    const userId = this.getAttribute('data-id');
                    window.location.href = `/admin-add-user.html?id=${userId}`;

                });
            });
        })
        .catch(error => console.error('Failed', error));
});

