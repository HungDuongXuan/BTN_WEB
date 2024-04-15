// Fix dữ liệu
const resultsData = [
    { exam: "exam1", participation: 50, completionRate: 80, averageScore: 75 },
    { exam: "exam2", participation: 60, completionRate: 90, averageScore: 85 },
    { exam: "exam3", participation: 65, completionRate: 100, averageScore: 75 },
    { exam: "exam4", participation: 50, completionRate: 90, averageScore: 85 },
    { exam: "exam5", participation: 10, completionRate: 90, averageScore: 45 },
    { exam: "exam6", participation: 80, completionRate: 100, averageScore: 67 }
    // Thêm dữ liệu cho các kỳ thi khác
];

// Tính tổng lần tham gia, tỷ lệ hoàn thành, và điểm trung bình
const totalParticipation = resultsData.reduce((sum, result) => sum + result.participation, 0);
const totalCompletionRate = resultsData.reduce((sum, result) => sum + result.completionRate, 0);
const totalAverageScore = resultsData.reduce((sum, result) => sum + result.averageScore, 0);

// Hiển thị dữ liệu cố định
document.getElementById("totalValue").textContent = totalParticipation;
document.getElementById("completionValue").textContent = `${Math.round(totalCompletionRate / resultsData.length)}%`;
document.getElementById("averageValue").textContent = Math.round(totalAverageScore / resultsData.length);

// ... (phần mã khác ở trên)

// Vẽ biểu đồ phân phối điểm số
const scoreChartCanvas = document.getElementById("scoreChart");
const scoreChartContext = scoreChartCanvas.getContext("2d");

const scoreLabels = resultsData.map(result => result.exam);
const scoreData = resultsData.map(result => result.averageScore);

new Chart(scoreChartContext, {
    type: "bar",
    data: {
        labels: scoreLabels,
        datasets: [{
            label: "Điểm trung bình",
            data: scoreData,
            backgroundColor: "rgba(75, 192, 192, 0.2)",
            borderColor: "rgba(75, 192, 192, 1)",
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        },
        responsive: true, // Cho phép biểu đồ tự động thích ứng
        // maintainAspectRatio: false, // Tắt giữ tỷ lệ khung hình
        aspectRatio: 2, // Tăng tỷ lệ khung hình lên gấp 2 (đối với chiều ngang)
    }
});

// ... (phần mã khác ở dưới)


// Hàm lọc kết quả theo kỳ thi
function filterResults() {
    const selectedExam = document.getElementById("examFilter").value;

    // Lọc dữ liệu tương ứng với kỳ thi được chọn
    const filteredResult = resultsData.find(result => result.exam === selectedExam);

    // Hiển thị dữ liệu lọc
    document.getElementById("totalValue").textContent = filteredResult.participation;
    document.getElementById("completionValue").textContent = `${filteredResult.completionRate}%`;
    document.getElementById("averageValue").textContent = filteredResult.averageScore;

    // Cập nhật biểu đồ
    new Chart(scoreChartContext, {
        type: "bar",
        data: {
            labels: [filteredResult.exam],
            datasets: [{
                label: "Điểm trung bình",
                data: [filteredResult.averageScore],
                backgroundColor: "rgba(75, 192, 192, 0.2)",
                borderColor: "rgba(75, 192, 192, 1)",
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// Hàm xuất PDF
function exportToPDF() {
    // Thêm mã xuất PDF ở đây
    alert("Đang xuất PDF...");
}

// Hàm xuất Excel
function exportToExcel() {
    // Thêm mã xuất Excel ở đây
    alert("Đang xuất Excel...");
}
