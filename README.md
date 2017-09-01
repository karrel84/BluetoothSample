# BluetoothSample
기기와 블루투스로 연결하여 리모콘 제어를 하기위한 앱을 만드는중에<br/>
SPP연결을 통해 테스트를 하는 앱중 원하는 기능이 있는 앱을 찾기 힘들어서 만들게 되었다.<br/>
통신은 android bluetooth chat sample 을 참고하여 만들었다.<br/>

# Screenshot
##### 메인화면
![Screenshot](https://github.com/karrel84/BluetoothSample/blob/master/screen/device-2017-08-30-170655.png?raw=true)
* 현재 블루투스 기기와의 연결상태를 표시하고 액션바의 기능에는 클리어, 상단고정, 블루투스 설정등이 있다
* 블루투스 기기와 연결되면 기기에서 보내는 바이트코드를 파싱해서 16진수로 표시한다

##### 블루투스 연결
![Screenshot](https://github.com/karrel84/BluetoothSample/blob/master/screen/device-2017-08-30-170719.png?raw=true)
* 페어링된 기기목록과 BLE를 통해 스캔되는 블루투스 기기에 대한 목록을 노출한다
* BLE스캔목록의 기기를 선택했하면 페어링을 맺고 연결을 시켜준다

##### 프로토콜 생성
![Screenshot](https://github.com/karrel84/BluetoothSample/blob/master/screen/device-2017-08-30-170736.png?raw=true)
* 생성되는 프로토콜의 이름을 입력받고 마지막 바이트가 체크섬이면 checksum last byte 체크박스를 체크하면 자동으로 마지막 바이트를 체크섬으로 만들어준다
