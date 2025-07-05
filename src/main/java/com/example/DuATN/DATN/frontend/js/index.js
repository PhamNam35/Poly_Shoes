let tatCaSanPham = [];
let sanPhamHienTai = [];
const sanPhamTrenTrang = 10;

document.addEventListener("DOMContentLoaded", () => {
  fetch("http://localhost:8080/san-pham")
    .then(res => res.json())
    .then(data => {
      tatCaSanPham = data;
      locSanPham();
    });
    const khachHangId = localStorage.getItem("khachHangId");
  if (khachHangId) {
    fetch(`http://localhost:8080/khach-hang/${khachHangId}`)
      .then(res => res.json())
      .then(khach => {
        const auth = document.getElementById("auth-area");
        auth.innerHTML = `
          <span style="color:white; margin-right: 10px;">
            👤 Xin chào, ${khach.hoTen} (Khách hàng)
          </span>
          <button onclick="window.location.href='dathang.html'">Giỏ hàng</button>
          <button onclick="dangXuat()">Đăng xuất</button>
        `;
      })
      .catch(err => {
        console.warn("Không thể tải thông tin khách hàng:", err);
      });
  }
});

function locSanPham() {
  const ten = document.getElementById("timKiemTen")?.value.toLowerCase() || "";
  const thuongHieu = document.getElementById("locThuongHieu")?.value || "";
  const kieu = document.getElementById("locKieuGiay")?.value || "";
  const locGia = document.getElementById("locGia")?.value || "";
  const locSize = document.getElementById("locSize")?.value || "";

  sanPhamHienTai = tatCaSanPham.filter(sp => {
    const tenHop = sp.tenSanPham?.toLowerCase().includes(ten);
    const thHop = !thuongHieu || sp.thuongHieu?.tenThuongHieu === thuongHieu;
    const kieuHop = !kieu || sp.kieuGiay?.tenKieuGiay === kieu;

    let giaHop = true;
    if (locGia === "duoi1tr") giaHop = sp.giaBan < 1000000;
    else if (locGia === "1-2tr") giaHop = sp.giaBan >= 1000000 && sp.giaBan <= 2000000;
    else if (locGia === "tren2tr") giaHop = sp.giaBan > 2000000;

    let sizeHop = true;
    if (locSize) {
      sizeHop = sp.chiTietList?.some(ct => ct.kichThuoc?.size == locSize);
    }

    return tenHop && thHop && kieuHop && giaHop && sizeHop;
  });

  renderTrang(1);
  renderPhanTrang();
}

function renderTrang(trang) {
  const container = document.getElementById("danhSachSanPham");
  container.innerHTML = "";

  const batDau = (trang - 1) * sanPhamTrenTrang;
  const hienThi = sanPhamHienTai.slice(batDau, batDau + sanPhamTrenTrang);

  hienThi.forEach(sp => {
    const card = document.createElement("div");
    card.className = "card";

    const img = document.createElement("img");
    img.src = sp.duongDanAnh || "img/default.jpg";
    img.onerror = () => (img.src = "img/default.jpg");

    const ten = document.createElement("h3");
    ten.textContent = sp.tenSanPham || "Không tên";

    const gia = document.createElement("p");
    gia.textContent = sp.giaBan ? sp.giaBan.toLocaleString() + " VNĐ" : "Giá: chưa cập nhật";

    const btnChiTiet = document.createElement("button");
    btnChiTiet.className = "btn-detail";
    btnChiTiet.textContent = "Xem chi tiết";
    btnChiTiet.onclick = () => {
      localStorage.setItem("spId", sp.sanPhamId);
      location.href = "customer/chiTiet.html";
    };

    const btnThem = document.createElement("button");
    btnThem.className = "btn-cart";
    btnThem.innerHTML = "🛒 Thêm vào giỏ";
    btnThem.onclick = () => themVaoGio(sp.sanPhamId);

    const btnMua = document.createElement("button");
    btnMua.className = "btn-buy";
    btnMua.textContent = "Mua ngay";
    btnMua.onclick = () => {
      themVaoGio(sp.sanPhamId);
      location.href = "dathang.html";
    };

    const group = document.createElement("div");
    group.className = "card-buttons";
    group.appendChild(btnChiTiet);

    const row = document.createElement("div");
    row.className = "btn-row";
    row.appendChild(btnThem);
    row.appendChild(btnMua);
    group.appendChild(row);

    card.appendChild(img);
    card.appendChild(ten);
    card.appendChild(gia);
    card.appendChild(group);

    container.appendChild(card);
  });
}

function renderPhanTrang() {
  const div = document.getElementById("phanTrang");
  div.innerHTML = "";

  const tong = Math.ceil(sanPhamHienTai.length / sanPhamTrenTrang);
  for (let i = 1; i <= tong; i++) {
    const btn = document.createElement("button");
    btn.textContent = i;
    btn.onclick = () => renderTrang(i);
    div.appendChild(btn);
  }
}

function themVaoGio(spId) {
  let gioHang = JSON.parse(localStorage.getItem("gioHang") || "[]");
  const found = gioHang.find(item => item.sanPhamId === spId);
  if (found) {
    found.soLuong += 1;
  } else {
    gioHang.push({ sanPhamId: spId, soLuong: 1 });
  }
  localStorage.setItem("gioHang", JSON.stringify(gioHang));
  alert("✅ Đã thêm vào giỏ hàng!");
}
function dangXuat() {
  localStorage.removeItem("khachHangId");
  location.reload();
}
