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
              implementation 'com.github.Wiser-Wong:WRouter:1.1.2'
              annotationProcessor 'com.github.Wiser-Wong:WRouter:1.1.2'
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
    
    @Router(path = "app/MainActivity")
    public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    }
    
    跳转
    WRouter.create("one/OneActivity").open(MainActivity.this);
    WRouter.create("two/TwoActivity").open(MainActivity.this,111==请求码);

    * IProvider使用
          public interface IMainProvider extends IProvider {

             void hello(String hello);

          }

          @Router(provider = "app/MainProvider")
          public class MainProvider implements IMainProvider {

              @Override
              public void init(Context context) {

              }

              @Override
              public void hello(String hello) {
                  System.out.println("------------->>" + hello);
              }
          }

          使用
          MainProvider mainProvider = (MainProvider) WRouter.create("app/MainProvider").buildProvider();
          if (mainProvider != null) mainProvider.hello("你好啊");

## 使用注意
   最好使用moduleName作为分类
   需要使用/来分割moduleName和页面

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
