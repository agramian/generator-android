var generators = require('yeoman-generator');

module.exports = generators.Base.extend({
  prompting: function () {
      return this.prompt([{
        type    : 'input',
        name    : 'org',
        message : 'Organization name for package com.[org].app',
        default : 'example'
      }, {
        type    : 'input',
        name    : 'appname',
        message : 'Application name for package com.org.[app]',
        default : 'app'
      }]).then(function (answers) {
        // copy common
        this.fs.copy(
          this.templatePath('common/**'),
          this.destinationRoot()
        )
        // rename gitignore to .gitignore
        this.fs.move(
          this.destinationPath('gitignore'),
          this.destinationPath('.gitignore')
        )
        // vars from answers
        var app_id = ['com', answers.org.trim(), answers.appname.trim()].join('.');
        var main_dir = 'app/src/main';
        var main_src_path = [main_dir, 'java', 'com', answers.org.trim(), answers.appname.trim()].join('/');
        // list of source dir, file name, destination dir, destination file name (uses original file name if not provided)
        var dynamic_files = [
          ['AndroidManifest.xml', main_dir],
          ['project_build.gradle', './', 'build.gradle'],
          ['app_build.gradle', 'app', 'build.gradle'],
          ['proguard-rules.pro', 'app'],
          ['proguardTest-rules.pro', 'app'],
          ['MainApplication.java', main_src_path],
          ['SplashActivity.java', main_src_path],
          [['cache', 'UserCache.java'].join('/'), main_src_path],
          [['di/component', 'AppComponent.java'].join('/'), main_src_path],
          [['di/component', 'UserComponent.java'].join('/'), main_src_path],
          [['di/module', 'AppModule.java'].join('/'), main_src_path],
          [['di/module', 'UserModule.java'].join('/'), main_src_path],
          [['di/scope', 'FragmentScope.java'].join('/'), main_src_path],
          [['di/scope', 'UserScope.java'].join('/'), main_src_path],
          ['feature/start/di/StartComponent.java', main_src_path],
          ['feature/start/di/StartModule.java', main_src_path],
          ['feature/start/StartActivity.java', main_src_path],
          ['feature/start/StartContract.java', main_src_path],
          ['feature/start/StartFragment.java', main_src_path],
          ['feature/start/StartPresenter.java', main_src_path],
          ['feature/home/di/HomeComponent.java', main_src_path],
          ['feature/home/di/HomeModule.java', main_src_path],
          ['feature/home/HomeActivity.java', main_src_path],
          ['feature/home/HomeContract.java', main_src_path],
          ['feature/home/HomeFragment.java', main_src_path],
          ['feature/home/HomePresenter.java', main_src_path],
          [['model', 'User.java'].join('/'), main_src_path],
          [['model', 'UserManager.java'].join('/'), main_src_path],
          [['ui', 'BaseActivity.java'].join('/'), main_src_path],
          [['ui', 'BaseFragment.java'].join('/'), main_src_path],
          [['ui', 'BasePresenter.java'].join('/'), main_src_path],
          [['ui', 'BaseUserActivity.java'].join('/'), main_src_path],
          [['ui', 'BaseUserFragment.java'].join('/'), main_src_path],
          [['ui', 'BaseView.java'].join('/'), main_src_path],
          [['ui', 'LoadingListener.java'].join('/'), main_src_path],
          [['ui', 'LoadingObserver.java'].join('/'), main_src_path]
        ];
        // copy dynamic
        var self = this;
        dynamic_files.forEach(function each(item) {
          self.log(item[0], item[1], item[2]);
          self.fs.copyTpl(
                self.templatePath(['dynamic', item[0]].join('/')),
                self.destinationPath([item[1], item[2] != undefined ? item[2] : item[0]].join('/')), {
                    app_id: app_id
                }
            );
        });
      }.bind(this));
    }
});
