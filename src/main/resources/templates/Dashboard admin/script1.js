let examList = ['2021-1', '2021-2', '2022-1', '2022-2'];
let userList = [
    {username : 'dang',password : '123'},
    {username : 'nguyenquang2311',password : '123'},
    {username : 'TTT',password : '1'}
]
function showExam(){
    let examListHTML = '<ul id="list_exam">';
    examList.forEach(exam => {
        examListHTML += `<li>${exam}</li>`;
    });
    examListHTML += '</ul>';

    showModal(examListHTML);
}


function addExam() {
    const examToAdd = prompt("Nhập kỳ thi cần thêm: ");
    if (examToAdd) {
        const index = examList.indexOf(examToAdd);
        if(index !== -1){
            alert("Kì thi đã tồn tại");
        } else {
            // Thêm kỳ thi vào mảng exams
            examList.push(examToAdd);

            alert("Thêm kì thi thành công");

            // Hiển thị lại danh sách kỳ thi
            displayExams(examList);
        } 
    } else {
        alert('Vui lòng nhập tên kỳ thi.');
    }
}


function showModal(content) {
    const modal = document.getElementById('myModal');
    const modalContent = modal.querySelector('.modal-content');
    modalContent.innerHTML = content;
    modal.style.display = 'block';
}

function closeModal(modal) {
    modal.style.display = 'none';
}
function deleteExam() {
    const examToDelete = prompt('Nhập tên kỳ thi cần xoá:');
    const index = examList.indexOf(examToDelete);
    if (index !== -1) {
        examList.splice(index, 1);
        alert("Đã xoá kì thi.")
    } else {
        alert('Không tìm thấy kỳ thi cần xoá.');
    }
}
// function displayExams(examArray) {
//     const examListElement = document.getElementById('exam_list');
//     examListElement.innerHTML = '';
//     const examListHTML = '<ul id="list_exam">';
//     examArray.forEach(exam => {
//         examListHTML += `<li>${exam.value} </li>`;
//     });
//     examListHTML += '</ul>';
//     examListElement.innerHTML = examListHTML;
//     showModal(examListHTML);
// }

function closeAndClearModal(modal) {
    closeModal(modal);
    clearModalContent(modal);
}

function clearModalContent(modal) {
    const modalContent = modal.querySelector('.modal-content');
    modalContent.innerHTML = '';  // Xóa nội dung modal
}

window.onclick = function (event) {
    const modal_1 = document.getElementById('myModal');
    const modal_2 = document.getElementById('addUserModal');
    if (event.target == modal_1 || event.target == modal_2) {
        closeAndClearModal(modal_1);
        // closeAndClearModal(modal_2);
    }
}

const closeButton = document.querySelector('.close');
if (closeButton) {
    closeButton.onclick = closeAndClearModal;
}
function editExam(){
    const examToAdd = prompt("Nhập kỳ thi cần sửa: ");
    if (examToAdd) {
        const index = examList.indexOf(examToAdd);
        if(index == -1){
            alert("Không tồn tại kì thi cần sửa");
        } else {
            examList.splice(index, 1);
            let examToEdit = prompt("Sửa kì thi '"+examToAdd+"': ");
            while(true){
                if(examToAdd != examToEdit && examList.indexOf(examToEdit) == -1){
                    examList.push(examToEdit);
                    break;
                }
                else{
                    examToEdit = prompt("kì thi đã tồn tại, bạn hãy nhập lại");
                }
            }
            
            alert("Sửa kì thi thành công");
            displayExams(examList);
        } 
    } else {
        alert('Vui lòng nhập tên kỳ thi.');
    }

}







function showUser(){
    let userListHTML = '<ul id= "list_exam">';
    userList.forEach(user => {
        userListHTML += `<li>${user.username}</li>`;
    });
    userListHTML += '</ul>';
    showModal(userListHTML);
}

function openAddUserModal(){
    const addUserModal = document.getElementById('addUserModal');
    // clearModalContent(addUserModal);  // Xóa nội dung cũ trước khi mở modal
    addUserModal.style.display = "flex";
}

function addUser() {
    const userInput = document.getElementById("username");
    const userName = userInput.value;

    const passInput = document.getElementById("password");
    const passWord = passInput.value;
    const index = userList.findIndex(user => user.username === userName);

    if (index == -1) {
        userList.push({ username: userName, password: passWord });
        userInput.value = '';
        passInput.value = '';
        alert("Thêm người dùng mới thành công");

    } else {
        alert("Đã tồn tại người dùng trước đó");
    }
    // closeAndClearModal(document.getElementById('addUserModal'));
}

function closeAddUserModal() {
    const modal = document.getElementById('addUserModal');
    modal.style.display = 'none';
    window.onclick = null; // Xóa sự kiện click
}

window.onclick = function (event) {
    const modal = document.getElementById('myModal');
    const addUserModal = document.getElementById('addUserModal');
    if (event.target === modal || event.target === addUserModal) {
        closeAndClearModal(modal);
        closeModal(addUserModal);
    }
}

function deleteUser(){
    const userToDelete = prompt('Nhập tên người dùng cần xoá:');
    const index = userList.findIndex(user => user.username === userToDelete);
    if (index !== -1) {
        userList.splice(index, 1);
        alert("Đã xoá người dùng.");
    } else {
        alert('Không tìm thấy người dùng cần xoá.');
    }
}

function editUser(){
    const userToEdit = prompt("Nhập người dùng cần sửa: ");
    if (userToEdit) {
        const index = userList.findIndex(user => user.username === userToEdit);
        if(index == -1){
            alert("Không tồn tại người dùng cần sửa");
        } else {
            userList.splice(index, 1);

            const usernameNew = prompt("Nhập tên người dùng mới");
            const passwordNew = prompt("Nhập mật khẩu mới");
            const emailNew = prompt("Nhập email mới");

            userList.push({username: usernameNew, password: passwordNew})
            alert("Sửa người dùng thành công");
            displayExams(examList);

        } 
    } else {
        alert('Vui lòng nhập tên kỳ thi.');
    }
}


function viewStatistics() {
    window.location.href="../Thống kê/Thống kê.html";
}



// Xử lý sự kiện hover để hiển thị menu khi di chuột đến biểu tượng menu
document.querySelector('.navbar-toggler').addEventListener('mouseover', function() {
    document.querySelector('.navbar-collapse').style.display = 'block';
});

// Ẩn menu khi di chuột rời khỏi biểu tượng menu
document.querySelector('.navbar-toggler').addEventListener('mouseout', function() {
    document.querySelector('.navbar-collapse').style.display = 'none';
});


// Bắt sự kiện click ra ngoài modal để đóng modal
window.addEventListener('click', function(event) {
    var modal = document.getElementById('exampleModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
});
  
  // Bắt sự kiện click vào nút mở modal để mở modal
var button = document.querySelector('.btn-primary');
button.addEventListener('click', function() {
    var modal = document.getElementById('exampleModal');
    modal.style.display = 'block';
});

// đóng modal khi nhấn lưu
document.addEventListener('DOMContentLoaded', function () {
    var saveButton = document.querySelector('#exampleModal.modal-footer.#luu');
    saveButton.addEventListener('click', function () {
        $('#exampleModal').modal('hide');
    });
});
  