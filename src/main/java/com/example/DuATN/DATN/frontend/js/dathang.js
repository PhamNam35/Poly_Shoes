function datHang() {
  const khachHangId = localStorage.getItem("khachHangId");
  const thongBaoEl = document.getElementById("thongbao");

  const gioHang = JSON.parse(localStorage.getItem("gioHang") || "[]");

  if (gioHang.length === 0) {
    thongBaoEl.innerText = "❌ Giỏ hàng trống.";
    return;
  }

  const chiTietSpIds = gioHang.map(item => item.sanPhamId);
  const soLuongs = gioHang.map(item => item.soLuong || 1);

  // Nếu đã đăng nhập
  if (khachHangId) {
    fetch("http://localhost:8080/dat-hang", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify({
        khachHangId: parseInt(khachHangId),
        chiTietSpIds,
        soLuongs
      })
    })
      .then(res => res.json())
      .then(data => {
        thongBaoEl.innerText =
          `✅ Đặt hàng thành công! Mã đơn: ${data.donHangId}, Tổng tiền: ${data.tongTien} VNĐ`;
        localStorage.removeItem("gioHang");
      })
      .catch(() => {
        thongBaoEl.innerText = "❌ Đặt hàng thất bại.";
      });

  } else {
    // Nếu chưa đăng nhập, yêu cầu người dùng nhập thông tin giao hàng
    const hoTen = document.getElementById("hoTen")?.value;
    const soDienThoai = document.getElementById("soDienThoai")?.value;
    const diaChi = document.getElementById("diaChi")?.value;

    if (!hoTen || !soDienThoai || !diaChi) {
      thongBaoEl.innerText = "❌ Vui lòng nhập đầy đủ thông tin giao hàng.";
      return;
    }

    fetch("http://localhost:8080/dat-hang-anonymous", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify({
        hoTen,
        soDienThoai,
        diaChiGiaoHang: diaChi,
        chiTietSpIds,
        soLuongs
      })
    })
      .then(res => res.json())
      .then(data => {
        thongBaoEl.innerText =
          `✅ Đặt hàng thành công! Mã đơn: ${data.donHangId}, Tổng tiền: ${data.tongTien} VNĐ`;
        localStorage.removeItem("gioHang");
      })
      .catch(() => {
        thongBaoEl.innerText = "❌ Đặt hàng thất bại.";
      });
  }
}
