document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('addUserForm');
    const urlParams = new URLSearchParams(window.location.search);
    const userId = urlParams.get('id');

    // Загружаем роли сначала
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

            if (userId) {
                // Если редактируем пользователя, загружаем его данные
                fetch(`/admin/${userId}`)
                    .then(response => response.json())
                    .then(user => {
                        document.getElementById('firstName').value = user.firstName;
                        document.getElementById('lastName').value = user.lastName;
                        document.getElementById('age').value = user.age;
                        document.getElementById('email').value = user.email;

                        // Устанавливаем роли пользователя
                        user.roles.forEach(role => {
                            const option = rolesSelect.querySelector(`option[value="${role.id}"]`);
                            if (option) {
                                option.selected = true;
                            }
                        });

                        // Обновляем заголовок формы и кнопку
                        document.querySelector('.card-title').textContent = 'Update user';
                        document.querySelector('button[type="submit"]').textContent = 'Update user';
                    })
                    .catch(error => console.error('Failed to load user data:', error));
            }
        })
        .catch(error => console.error('Ошибка при загрузке ролей:', error));

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

            // Определяем метод и URL в зависимости от наличия userId
            const method = userId ? 'PUT' : 'POST';
            const url = userId ? `/admin/update-user/${userId}` : '/admin/add-user';

            fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            })
                .then(response => {
                    if (response.ok) {
                        window.location.href = '/admin-page.html';
                    } else {
                        console.error('Failed to save user');
                    }
                })
                .catch(error => console.error('Failed:', error));
        });
    }
});