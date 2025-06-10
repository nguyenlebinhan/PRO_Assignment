

```markdown
# Hướng dẫn làm việc với Git

## 🚫 Không commit trực tiếp vào nhánh `main`
Để đảm bảo chất lượng code và quy trình làm việc chuyên nghiệp, **không commit trực tiếp** vào nhánh `main`. Hãy sử dụng **branch** riêng cho từng tính năng hoặc lỗi cần sửa.

## 🔀 Hướng dẫn Merge
### 1. Kiểm tra trạng thái nhánh hiện tại
Trước khi merge, cần chắc chắn bạn đang làm việc trên đúng nhánh:
```bash
git branch
```

### 2. Chuyển về nhánh `main` (hoặc nhánh chính cần merge)
```bash
git checkout main
git pull origin main  # Cập nhật nhánh main với phiên bản mới nhất
```

### 3. Merge nhánh làm việc vào `main`
```bash
git merge ten-nhanh
```

Nếu có xung đột, giải quyết thủ công, sau đó:
```bash
git add .
git commit -m "Giải quyết xung đột"
```

### 4. Đẩy thay đổi lên repository
```bash
git push origin main
```

## 🌿 Cách tạo branch mới để làm việc
### 1. Tạo một nhánh mới
```bash
git checkout -b ten-nhanh-moi
```

### 2. Đẩy nhánh mới lên repository
```bash
git push -u origin ten-nhanh-moi
```

Sau khi tạo xong branch, có thể làm việc trên nhánh đó mà không ảnh hưởng đến `main`.

---

🛠 **Ghi nhớ:**  
- Luôn kiểm tra trạng thái branch trước khi commit: `git status`
- Thực hiện `git pull` thường xuyên để cập nhật code mới từ nhánh chính.
- Sử dụng **pull request** để đảm bảo code được review trước khi merge vào `main`.

