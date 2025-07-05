function loadGioHang() {
  const khachHangId = 1;
  fetch(`http://localhost:8080/gio-hang/${khachHangId}`)
    .then(res => res.json())
    .then(data => {
      let html = "<ul>";
      data.forEach(item => {
        html += `<li>${item.sanPham.tenSanPham} - ${item.phanLoai}</li>`;
      });
      html += "</ul>";
      document.getElementById("ketqua").innerHTML = html;
    });
}

function themVaoGio(spId, phanLoai) {
  const khachHangId = localStorage.getItem("khachHangId");

  if (khachHangId) {
    // đăng nhập → dùng API
    fetch("http://localhost:8080/gio-hang/them", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify({ khachHangId: parseInt(khachHangId), sanPhamId: spId, phanLoai })
    }).then(res => res.json()).then(() => alert("Đã thêm vào giỏ"));
  } else {
    // khách ẩn danh → lưu localStorage
    let gioHang = JSON.parse(localStorage.getItem("gioHang") || "[]");
    gioHang.push({ sanPhamId: spId, phanLoai, soLuong: 1 });
    localStorage.setItem("gioHang", JSON.stringify(gioHang));
    alert("Đã thêm vào giỏ (khách chưa đăng nhập)");
  }
}

function loadGioHang() {
  const khachHangId = localStorage.getItem("khachHangId");
  if (!khachHangId) {
    alert("Vui lòng đăng nhập trước!");
    window.location.href = "login.html";
    return;
  }

  fetch(`http://localhost:8080/gio-hang/${khachHangId}`)
    .then(res => res.json())
    .then(data => {
      // render giỏ hàng
    });
}
function dangXuat() {
  localStorage.removeItem("khachHangId");
  window.location.href = "login.html";
}
