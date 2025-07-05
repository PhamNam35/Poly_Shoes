function taiLichSu() {
  const khachHangId = localStorage.getItem("khachHangId") || 1; // mặc định ID = 1 nếu chưa đăng nhập

  fetch(`http://localhost:8080/dat-hang/${khachHangId}`)
    .then(res => res.json())
    .then(data => {
      const listDiv = document.getElementById("donhang-list");
      if (!data.length) {
        listDiv.innerHTML = "<p>Chưa có đơn hàng nào.</p>";
        return;
      }

      let html = "<ul>";
      data.forEach(dh => {
        html += `
          <li>
            <b>Đơn hàng #${dh.donHangId}</b><br/>
            Ngày đặt: ${dh.ngayDatHang}<br/>
            Trạng thái: ${dh.trangThai}<br/>
            Tổng tiền: ${dh.tongTien} VNĐ<br/>
            Thanh toán: ${dh.trangThaiThanhToan}<br/>
            Địa chỉ: ${dh.diaChiGiaoHang}<br/>
            Voucher: ${dh.voucher ? dh.voucher.loai : "Không"}
            <hr/>
          </li>
        `;
      });
      html += "</ul>";

      listDiv.innerHTML = html;
    })
    .catch(err => {
      console.error("Lỗi tải đơn hàng:", err);
      document.getElementById("donhang-list").innerText = "Lỗi tải dữ liệu.";
    });
}
function taiLichSu() {
  const khachHangId = localStorage.getItem("khachHangId");
  if (!khachHangId) {
    alert("Vui lòng đăng nhập để xem lịch sử đơn hàng.");
    window.location.href = "login.html";
    return;
  }

  fetch(`http://localhost:8080/dat-hang/${khachHangId}`)
    .then(res => res.json())
    .then(data => {
      // render danh sách đơn hàng
    });
}
function dangXuat() {
  localStorage.removeItem("khachHangId");
  window.location.href = "login.html";
}
