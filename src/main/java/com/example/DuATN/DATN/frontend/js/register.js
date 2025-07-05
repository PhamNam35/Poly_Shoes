function dangKy(event) {
  event.preventDefault();

  const hoTen = document.getElementById("hoTen").value;
  const tenDangNhap = document.getElementById("username").value;
  const matKhau = document.getElementById("password").value;
  const vaiTro = document.getElementById("vaiTro").value;

  const data = { hoTen, tenDangNhap, matKhau };
  const endpoint = vaiTro === "khach"
    ? "/auth/register-customer"
    : "/auth/register-user";

  fetch("http://localhost:8080" + endpoint, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  })
    .then(res => {
      if (!res.ok) {
        return res.text().then(text => { throw new Error(text); });
      }
      return res.json();
    })
    .then(() => {
      // ✅ Sau khi đăng ký xong → gọi lại API đăng nhập tự động
      const loginUrl = vaiTro === "khach"
        ? "/auth/customer"
        : "/auth/admin";

      return fetch("http://localhost:8080" + loginUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username: tenDangNhap, password: matKhau })
      });
    })
    .then(res => {
      if (!res.ok) throw new Error("Đăng nhập sau đăng ký thất bại");
      return res.json();
    })
    .then(data => {
      if (vaiTro === "khach") {
        localStorage.setItem("khachHangId", data.khachHangId);
        window.location.href = "index.html";
      } else {
        localStorage.setItem("nguoiDungId", data.nguoiDungId || data.id || data.tenDangNhap); // tuỳ backend trả gì
        window.location.href = "admin.html";
      }
    })
    .catch(err => {
      document.getElementById("thongbao").innerText = "❌ " + err.message;
    });
}
