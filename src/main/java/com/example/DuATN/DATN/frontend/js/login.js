function dangNhap(e) {
  e.preventDefault(); // Ngăn reload form

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  const role = document.getElementById("vaiTro").value;

  const endpoint =
    role === "nhanvien" ? "/auth/admin" : "/auth/customer";

  fetch("http://localhost:8080" + endpoint, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password })
  })
    .then(res => {
      if (!res.ok) throw new Error("Sai tài khoản hoặc mật khẩu");
      return res.json();
    })
    .then(data => {
      if (role === "nhanvien") {
        localStorage.setItem("nguoiDungId", data.nguoiDungId);
        localStorage.setItem("vaiTro", "NHANVIEN");
        window.location.href = "admin.html";
      } else {
        localStorage.setItem("khachHangId", data.khachHangId);
        localStorage.setItem("vaiTro", "KHACH");
        window.location.href = "index.html";
      }
    })
    .catch(err => {
      document.getElementById("ketqua").innerText = "❌ " + err.message;
    });
}
