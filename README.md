## アプリケーションの設定
### application.yml
各自の環境に合わせてapplication-dev-example.ymlを参考にapplication-dev.ymlを作成してください。 

## アプリケーションの起動
### 開発環境におけるProfileの設定方法
SpringBootのアプリケーションを選択し、「Edit Configurations...」を選択します。  
![](doc/img/ActiveProfile1.PNG)

Active Profileに「dev」を入力してください。  
![](doc/img/ActiveProfile2.PNG)

## ログイン情報
* 一般ユーザ
  * ID: user
  * PW: user

* 管理ユーザ
  * ID: admin
  * PW: admin

## URL
* http://localhost
  * 全ユーザアクセス可
* http://localhost/menu
  * 管理ユーザのみアクセス可
