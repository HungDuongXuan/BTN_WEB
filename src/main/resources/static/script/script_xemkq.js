document.querySelector('.input-text').addEventListener('keypress', function (event) {
    if (event.key === 'Enter') {
        event.preventDefault();
        findUser();
    }
});

function findUser() {
    var input = document.querySelector(".input-text");
    var userId = input.value;
    if (isNaN(userId)) {
        alert('Mã định danh không hợp lệ!');
        location.reload();
        return;
    }
    loadUserInfo(userId);
    loadJoinedExam(userId);
    loadAllExamsResult(userId);
}

function loadUserInfo(id) {
    var infoBox = document.querySelector(".info-box");
    fetch(`/users/searchById?id=${id}`)
        .then(response => response.json())
        .then(data => {
            let gender;
            if (data.gender === true) {
                gender = "Nam";
            }
            else {
                gender = "Nữ";
            }
            var info = `<div class="info">
            <div class="profile-img-container">
                <img src="../img/profile.jpg">
            </div>
            <div class="profile-content">
                <div class="profile-detail">Họ tên: ${data.name}</div>
                <div class="profile-detail">Email: ${data.email}</div>
                <div class="profile-detail">Giới tính: ${gender}</div>
                <div class="profile-detail">Mã định danh: ${data.id}</div>
            </div>
        </div>`;
            infoBox.innerHTML = info;
        })
        .catch(error => {
            console.error("Error:", error);
            alert('Không tồn tại người dùng!');
            location.reload();
        });
}

function loadJoinedExam(id) {
    var examJoinedBox = document.querySelector('.exam-joined-box');
    var content = ``;
    fetch(`/contests/searchDetailByIdUser?id=${id}`)
        .then(response => response.json())
        .then(data => {

            content = `
                <table id="exam-table" border="1px">
                <tr>
                <th>Tên kỳ thi</th>
                <th>Trạng thái</th>
                <th>Điểm số</th>
                </tr>
                `;
            data.forEach(e => {
                content +=
                    `<tr>
                    <td>${e.exam.name}</td>
                    <td>Hoàn thành</td>
                    <td>${e.score}</td>
                    </tr>`;
            })
            content += `</table>`;
            examJoinedBox.innerHTML = content;


        })
        .catch(error => {
            examJoinedBox.innerHTML = '<div style="font-size: 30px">Người dùng chưa tham gia kỳ thi nào!</div>';
            document.querySelector('.exam-detail-box').innerHTML = '';
            console.error("Error:", error);
        });
}


async function loadAllExamsResult(id) {
    let userContests = await (await fetch(`/contests/searchDetailByIdUser?id=${id}`)).json();
    var examDetailBox = document.querySelector('.exam-detail-box');
    var content = await getInnerContent(userContests);
    examDetailBox.innerHTML = content;
}

async function getInnerContent(u)
{
    var temp = ``;
    for(let i = 0; i < u.length; i++)
    {
        c = u[i];
        var temp =
        `<div class="test">
            <h2>${c.exam.name}</h2>`;
    let userAnswers = await (await fetch(`/userAnswers/searchDetailByIdContest?id=${c.id}`)).json();
    userAnswers.answerQuestion.forEach(q => {
        var question =
        `<div class="question">
        <h3 class="question-name">Câu hỏi: ${q.question.content}</h3>
        <div class="answer">
        <div>Đáp án 1: ${q.question.answer1}</div>
        <div>Đáp án 2: ${q.question.answer2}</div>
        <div>Đáp án 3: ${q.question.answer3}</div>
        <div>Đáp án 4: ${q.question.answer4}</div>
        </div>
        <div class="selected-answer">Đã chọn: Đáp án ${q.userAnswer}</div>
        <div class="correct-answer">Đáp án đúng: Đáp án ${q.question.rightAnswer}</div>
        <div class="explaination">
        ${q.question.explain}
        </div>
        </div>`;
        temp += question;
    });
    temp += `<div class="export-box">
    <button>Xuất PDF</button>
    <button>Xuất Excel</button>
    </div>
    </div>`;
    }
    return temp;
}