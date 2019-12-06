# WRouter
路由跳转

## 环境配置
    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
      }

        dependencies {
              implementation 'com.github.Wiser-Wong:WRouter:1.1.0'
              annotationProcessor 'com.github.Wiser-Wong:WRouter:1.1.0'
      }
      
## 使用注意
如果报Static interface methods are only supported starting with Android N (--min-api 24)错误：在build.gradle添加下面代码

      android{
          compileOptions {
              sourceCompatibility JavaVersion.VERSION_1_8
              targetCompatibility JavaVersion.VERSION_1_8
          }
      }
      
## 使用说明
    public class MApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            WRouter.init(this);
        }
    }
    
    @Router("app:MainActivity")
    public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    }
    
    跳转
    WRouter.create("app:OtherActivity").open(MainActivity.this);

* withClose:是否关闭当前Activity
* withIntent
* withBundle
* withString
* withInt
* ~
* ~
* ~
* withParcelable
等等...
