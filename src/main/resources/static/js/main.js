// Placeholder file for JavaScript utilities
// JS có thể được thêm vào đây trong các bài thực hành tiếp theo

// Ví dụ: Xử lý form filter tự động submit khi thay đổi select
document.addEventListener('DOMContentLoaded', function () {
    // Auto submit khi thay đổi select danh mục (tuỳ chọn)
    const categorySelect = document.getElementById('categoryId');
    if (categorySelect) {
        // categorySelect.addEventListener('change', function () {
        //     document.getElementById('filterForm').submit();
        // });
    }

    // Highlight sản phẩm khi hover
    const productCards = document.querySelectorAll('.product-card');
    productCards.forEach(card => {
        card.addEventListener('mouseenter', function () {
            this.style.zIndex = '10';
        });
        card.addEventListener('mouseleave', function () {
            this.style.zIndex = '';
        });
    });
});
