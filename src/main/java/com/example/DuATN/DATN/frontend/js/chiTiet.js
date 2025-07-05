document.addEventListener("DOMContentLoaded", () => {
  const id = localStorage.getItem("spId");

  fetch(`http://localhost:8080/san-pham/${id}`)
    .then(res => res.json())
    .then(sp => {
      document.getElementById("spImg").src = sp.duongDanAnh || "../img/default.jpg";
      document.getElementById("spImg").onerror = () => {
        document.getElementById("spImg").src = "../img/default.jpg";
      };

      document.getElementById("spTen").textContent = sp.tenSanPham;
      document.getElementById("spGia").textContent = sp.giaBan?.toLocaleString() + " VNĐ";
      document.getElementById("spChatLieu").textContent = "Chất liệu: " + sp.chatLieu;
      document.getElementById("spMoTa").textContent = "Mô tả: " + sp.moTa;
    })
    .catch(err => {
      console.error("❌ Lỗi khi tải chi tiết:", err);
    });
});
