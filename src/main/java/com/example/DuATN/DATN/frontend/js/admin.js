function taoDonOffline() {
  const khachHangId = parseInt(document.getElementById("khachHangId").value);
  const chiTietSpIds = document.getElementById("chiTietSpIds").value.split(',').map(Number);
  const soLuongs = document.getElementById("soLuongs").value.split(',').map(Number);

  fetch("http://localhost:8080/admin/tao-don", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({ khachHangId, chiTietSpIds, soLuongs })
  })
    .then(res => res.json())
    .then(data => {
  console.log("RESP từ API:", data); // 👈 Thêm dòng này để xem cấu trúc thực tế
        
      document.getElementById("ketqua").innerText =
        `✅ Tạo đơn thành công! Mã đơn: ${data.donHangId}, Tổng tiền: ${data.tongTien} VNĐ`;
    })
    .catch(err => {
      console.error(err);
      document.getElementById("ketqua").innerText = "❌ Tạo đơn thất bại.";
    });
}
function laySanPham() {
  console.log("▶️ Gửi request GET /san-pham/admin");

  fetch("http://localhost:8080/san-pham/admin")
    .then(res => {
      console.log("📥 response status:", res.status);
      return res.json();
    })
    .then(data => {
      console.log("📦 Dữ liệu nhận về:", data);

      if (!Array.isArray(data)) {
        document.getElementById("ketqua").innerText = "❌ Dữ liệu không hợp lệ.";
        return;
      }

      const tbody = document.querySelector("#bangSanPham tbody");
      tbody.innerHTML = "";
      data.forEach(sp => {
        const row = `<tr>
          <td>${sp.sanPhamId}</td>
          <td>${sp.tenSanPham}</td>
          <td>${sp.gia || "?"}</td>
          <td>${sp.trangThai ? "✅" : "❌"}</td>
          <td><button onclick="xoaSanPham(${sp.sanPhamId})">Xoá</button></td>
        </tr>`;
        tbody.innerHTML += row;
      });
    })
    .catch(err => {
      console.error("❌ Lỗi fetch:", err);
    });
}

function themSanPham() {
  const sp = layThongTinTuForm();
  fetch("http://localhost:8080/san-pham/admin", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(sp)
  })
    .then(() => laySanPham());
}

function suaSanPham() {
  const sp = layThongTinTuForm();
  const id = document.getElementById("sp_id").value;
  fetch(`http://localhost:8080/san-pham/admin/${id}`, {
    method: "PUT",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(sp)
  })
    .then(() => laySanPham());
}

function xoaSanPham(id) {
  fetch(`http://localhost:8080/san-pham/admin/${id}`, {
    method: "DELETE"
  }).then(() => laySanPham());
}

function layThongTinTuForm() {
  return {
    tenSanPham: document.getElementById("ten_sp").value,
    chatLieu: document.getElementById("chat_lieu").value,
    moTa: document.getElementById("mo_ta").value,
    ngayNhap: document.getElementById("ngay_nhap").value,
    trangThai: document.getElementById("trang_thai").checked,
    thuongHieuId: parseInt(document.getElementById("thuong_hieu_id").value),
    kieuGiayGiayId: parseInt(document.getElementById("kieu_giay_id").value),
    xuatXuId: parseInt(document.getElementById("xuat_xu_id").value),
  };
}
