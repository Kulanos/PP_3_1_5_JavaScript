document.addEventListener("DOMContentLoaded", function () {
    function fetchUsers() {
        fetch('/admin')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.querySelector('#user-table-body');
                tableBody.innerHTML = '';
                data.forEach(user => {
                    const row = document.createElement('tr');
                    row.dataset.id = user.id;
                    row.innerHTML = `
                        <td>${user.id}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>
                            <ul>${user.roles.map(role => `<li>${role.roleName}</li>`).join('')}</ul>
                        </td>
                        <td>
                            <button class="btn btn-danger btn-sm delete-btn" data-id="${user.id}">Delete</button>
                            <button class="btn btn-primary btn-sm update-btn" data-id="${user.id}" data-bs-toggle="modal" data-bs-target="#userModal">Edit</button>
                        </td>`;
                    tableBody.appendChild(row);
                });

                attachEventHandlers();
            })
            .catch(error => console.error('Failed to load users:', error));
    }

    function attachEventHandlers() {
        document.querySelectorAll('.delete-btn').forEach(button => {
            button.addEventListener('click', function () {
                const userId = this.getAttribute('data-id');
                fetch(`/admin/delete-user/${userId}`, { method: 'DELETE' })
                    .then(response => {
                        if (response.ok) {
                            fetchUsers();
                        } else {
                            console.error('Failed to delete user');
                        }
                    });
            });
        });

        document.querySelectorAll('.update-btn').forEach(button => {
            button.addEventListener('click', function () {
                const userId = this.getAttribute('data-id');
                fetch(`/admin/${userId}`)
                    .then(response => response.json())
                    .then(user => {
                        document.getElementById('userModalLabel').textContent = 'Edit User';
                        document.getElementById('userId').value = user.id;
                        document.getElementById('firstName').value = user.firstName;
                        document.getElementById('lastName').value = user.lastName;
                        document.getElementById('age').value = user.age;
                        document.getElementById('email').value = user.email;

                        const rolesSelect = document.getElementById('roles');
                        rolesSelect.innerHTML = '';
                        fetch('/api/roles')
                            .then(response => response.json())
                            .then(roles => {
                                roles.forEach(role => {
                                    const option = document.createElement('option');
                                    option.value = role.id;
                                    option.text = role.roleName;
                                    if (user.roles.some(userRole => userRole.id === role.id)) {
                                        option.selected = true;
                                    }
                                    rolesSelect.appendChild(option);
                                });
                            });

                        document.getElementById('saveUserBtn').textContent = 'Edit';
                    });
            });
        });

        document.querySelector('#addUserLink').addEventListener('click', function () {
            document.getElementById('userModalLabelNew').textContent = 'Add New User';
            document.getElementById('userFormNew').reset();
            document.getElementById('rolesNew').innerHTML = '';

            fetch('/api/roles')
                .then(response => response.json())
                .then(roles => {
                    const rolesSelectNew = document.getElementById('rolesNew');
                    rolesSelectNew.innerHTML = '';
                    roles.forEach(role => {
                        const option = document.createElement('option');
                        option.value = role.id;
                        option.text = role.roleName;
                        rolesSelectNew.appendChild(option);
                    });
                });
        });

        document.getElementById('saveUserBtnNew').addEventListener('click', function () {
            const user = {
                firstName: document.getElementById('firstNameNew').value,
                lastName: document.getElementById('lastNameNew').value,
                age: document.getElementById('ageNew').value,
                email: document.getElementById('emailNew').value,
                roles: Array.from(document.getElementById('rolesNew').selectedOptions).map(option => ({
                    id: option.value,
                    roleName: option.text
                }))
            };

            fetch('/admin/add-user', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            })
                .then(response => {
                    if (response.ok) {
                        fetchUsers();
                        const modal = bootstrap.Modal.getInstance(document.getElementById('userModalNew'));
                        modal.hide();
                    } else {
                        console.error('Failed to add user');
                    }
                })
                .catch(error => console.error('Failed to add user:', error));
        });

        document.getElementById('saveUserBtn').addEventListener('click', function () {
            const userId = document.getElementById('userId').value;
            const user = {
                firstName: document.getElementById('firstName').value,
                lastName: document.getElementById('lastName').value,
                age: document.getElementById('age').value,
                email: document.getElementById('email').value,
                roles: Array.from(document.getElementById('roles').selectedOptions).map(option => ({
                    id: option.value,
                    roleName: option.text
                }))
            };

            fetch(`/admin/update-user/${userId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            })
                .then(response => {
                    if (response.ok) {
                        fetchUsers();
                        const modal = bootstrap.Modal.getInstance(document.getElementById('userModal'));
                        modal.hide();
                    } else {
                        console.error('Failed to update user');
                    }
                })
                .catch(error => console.error('Failed to update user:', error));
        });
    }

    fetchUsers();
});
