document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('addUserForm');

    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const user = {
                firstName: document.getElementById('firstName').value,
                lastName: document.getElementById('lastName').value,
                age: document.getElementById('age').value,
                email: document.getElementById('email').value,
                password: document.getElementById('password').value,
                roles: Array.from(document.getElementById('roles').selectedOptions).map(option => ({
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
                        window.location.href = '/admin-page.html';
                    } else {
                        console.error('Failed to add user');
                    }
                })
                .catch(error => console.error('Failed:', error));
        });

        fetch('/api/roles')
            .then(response => response.json())
            .then(data => {
                const rolesSelect = document.getElementById('roles');
                data.forEach(role => {
                    const option = document.createElement('option');
                    option.value = role.id;
                    option.text = role.roleName;
                    rolesSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Ошибка при загрузке ролей:', error));
    }
});