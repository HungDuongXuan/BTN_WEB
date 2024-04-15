document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById("login-form");
    const registerForm = document.getElementById("register-form");


    document.getElementById('register-link').addEventListener('click', function(event) {
        event.preventDefault();
        document.getElementById('register-popup').style.display = 'block';
    });

    document.getElementsByClassName('close')[0].addEventListener('click', function() {
        document.getElementById('register-popup').style.display = 'none';
    });


    loginForm.addEventListener("submit", function(event) {
        event.preventDefault();

        // Kiểm tra tính hợp lệ của thông tin đăng nhập
        if (validateLoginForm()) {
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            // Gửi yêu cầu đăng nhập đến endpoint POST /users/login
            fetch("/users/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(data => {
                // Xử lý dữ liệu trả về sau khi đăng nhập thành công
                console.log("Login successful:", data);
                // Chuyển hướng đến trang nào đó sau khi đăng nhập thành công
                window.location.href = "/exams/HomePageStu";
            })
            .catch(error => {
                // Xử lý lỗi trong quá trình đăng nhập
                console.error("Error logging in:", error);
                alert("Đăng nhập thất bại. Vui lòng thử lại.");
            });
        }
    });



    registerForm.addEventListener("submit", function(event) {
        event.preventDefault();

        if(ValidateRegisterForm()) {
            // Lấy giá trị từ các trường input
            const name = document.getElementById("nameRegister").value;
            const gender = (document.getElementById("genderRegister").value === "Nam") ? true : false;
            const username = document.getElementById("usernameRegister").value;
            const email = document.getElementById("emailRegister").value;
            const password = document.getElementById("passwordRegister").value;
            const confirmPassword = document.getElementById("confirmPasswordRegister").value;
            const roles = document.getElementById("roles").value;

            // Kiểm tra tính hợp lệ của mật khẩu
            if (password !== confirmPassword) {
                alert("Mật khẩu và xác nhận mật khẩu không khớp.");
                return;
            }

            // Gửi yêu cầu đăng ký đến endpoint POST /users/register
            fetch("/users/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name: name,
                    gender: gender,
                    username: username,
                    email: email,
                    password: password,
                    roles: roles
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.json();
            })
            .then(data => {
                // Xử lý dữ liệu trả về sau khi đăng ký thành công
                console.log("Registration successful:", data);
                alert("Đăng ký thành công!");
                // Thực hiện các hành động cần thiết sau khi đăng ký thành công
                // Ví dụ: Chuyển hướng đến trang chính sau khi đăng ký thành công
                if(data.roles === "admin") {

                }
                else {
                    window.location.pathname = "/exams/HomePageStu";
                }
                document.getElementById('register-popup').style.display = 'none';
            })
            .catch(error => {
                // Xử lý lỗi trong quá trình đăng ký
                console.error("Error registering:", error);
                alert("Đăng ký thất bại. Vui lòng thử lại.");
            });
        }
    });



    // Hàm kiểm tra tính hợp lệ của thông tin đăng nhập
    function validateLoginForm() {
        var username = document.getElementById("username").value.trim();
        var password = document.getElementById("password").value.trim();

        // Kiểm tra xem tên đăng nhập và mật khẩu có được nhập vào không
        if (username === "" || password === "") {
            alert("Vui lòng nhập tên đăng nhập và mật khẩu.");
            return false;
        }

        // Nếu thông tin đăng nhập hợp lệ, trả về true
        return true;
    }


    function ValidateRegisterForm() {
        // Lấy giá trị từ các trường input
        const name = document.getElementById("nameRegister").value;
        const gender = document.getElementById("genderRegister").value;
        const username = document.getElementById("usernameRegister").value;
        const email = document.getElementById("emailRegister").value;
        const password = document.getElementById("passwordRegister").value;
        const confirmPassword = document.getElementById("confirmPasswordRegister").value;
        const roles = document.getElementById("roles").value;

        // Kiểm tra tính hợp lệ của các trường
        if (name === "") {
            alert("Vui lòng nhập tên.");
            return false;
        }
        if (gender === "") {
            alert("Vui lòng chọn giới tính.");
            return false;
        }
        if (username === "") {
            alert("Vui lòng nhập tên đăng nhập.");
            return false;
        }
        if (email === "") {
            alert("Vui lòng nhập địa chỉ email.");
            return false;
        }
        // Kiểm tra định dạng email
        const emailRegex = /^\S+@\S+\.\S+$/;
        if (!emailRegex.test(email)) {
            alert("Địa chỉ email không hợp lệ.");
            return false;
        }
        if (password === "") {
            alert("Vui lòng nhập mật khẩu.");
            return false;
        }
        if (password.length < 6) {
            alert("Mật khẩu phải chứa ít nhất 6 ký tự.");
            return false;
        }
        if (confirmPassword !== password) {
            alert("Mật khẩu và xác nhận mật khẩu không khớp.");
            return false;
        }
        if (roles === "") {
            alert("Vui lòng chọn vai trò.");
            return false;
        }

        // Nếu tất cả các trường đều hợp lệ, trả về true để cho phép gửi yêu cầu
        return true;
    }


    // Hàm kiểm tra tính hợp lệ của thông tin đăng ký
    // function validateRegisterForm() {
    //     var newUsername = document.getElementById("new-username").value.trim();
    //     var email = document.getElementById("email").value.trim();
    //     var newPassword = document.getElementById("new-password").value.trim();
    //     var confirmPassword = document.getElementById("confirm-password").value.trim();

    //     // Kiểm tra xem các trường thông tin đăng ký có được nhập vào không
    //     if (newUsername === "" || email === "" || newPassword === "" || confirmPassword === "") {
    //         alert("Vui lòng điền đầy đủ thông tin.");
    //         return false;
    //     }

    //     // Kiểm tra định dạng email
    //     var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    //     if (!emailPattern.test(email)) {
    //         alert("Địa chỉ email không hợp lệ.");
    //         return false;
    //     }

    //     // Kiểm tra mật khẩu và xác nhận mật khẩu có khớp nhau không
    //     if (newPassword !== confirmPassword) {
    //         alert("Mật khẩu và xác nhận mật khẩu không khớp nhau.");
    //         return false;
    //     }

    //     // Nếu thông tin đăng ký hợp lệ, trả về true
    //     return true;
    // }





});

