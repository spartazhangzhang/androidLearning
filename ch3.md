## Activity 生命周期
    当设备旋转的时候，系统会销毁当前的activity实例，然后创建新的activity实力对象

`onSaveInstanceState(Bundle)方法`,系统随时会销毁已经停止的activity，暂存数据；
`onStop`,保存持久化的数据；
  
    