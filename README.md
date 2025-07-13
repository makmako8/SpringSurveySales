# SpringSurveySales

Spring Bootを使用して作成したアンケート回答アプリです。

## 📌 使用技術

- Java 17
- Spring Boot 3.x
- Spring Security (ログイン、認証)
- Thymeleaf
- Spring Data JPA
- Apache Commons CSV

## ✨ 主な機能

- ユーザーの登録・ログイン機能
- アンケートフォームの表示と回答のDB保存
- 回答結果をCSV形式でエクスポート（日本語対応済み）

## 🚩 新たに追加した機能（実務的な強化）

- 管理者ダッシュボードでアンケート結果を一覧表示
- JavaMailを利用したメール通知（アンケート回答通知）

### 🔔 メール通知機能について

アンケート送信時に管理者に通知メールを送ります。  
Spring Bootの **JavaMailSender** を使用しています。

（Gmail SMTPを使用）

## 🛠️ 技術的にポイントとなる箇所

- Gmail SMTPと連携し、アプリパスワードで安全にメール送信を実現
- メール送信エラー時の適切なエラー処理・例外対応
