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
        // list of file, destination dir, file name (uses original file name if not provided)
        var dynamic_files = [
          ['AndroidManifest.xml', main_dir],
          ['MainApplication.java', main_src_path],
          ['SplashActivity.java', main_src_path],
          ['StartActivity.java', main_src_path],
          ['HomeActivity.java', main_src_path],
          [['cache', 'UserCache.java'].join('/'), main_src_path],
          [['di/component', 'AppComponent.java'].join('/'), main_src_path],
          [['di/component', 'UserComponent.java'].join('/'), main_src_path],
          [['di/module', 'AppModule.java'].join('/'), main_src_path],
          [['di/module', 'UserModule.java'].join('/'), main_src_path],
          [['di/scope', 'UserScope.java'].join('/'), main_src_path],
          [['model', 'User.java'].join('/'), main_src_path],
          [['model', 'UserManager.java'].join('/'), main_src_path],
          [['ui', 'BaseActivity.java'].join('/'), main_src_path],
          [['ui', 'BaseUserActivity.java'].join('/'), main_src_path],
          ['project_build.gradle', './', 'build.gradle'],
          ['app_build.gradle', 'app', 'build.gradle']
        ];
        // copy dynamic
        var self = this;
        dynamic_files.forEach(function each(pair) {
          self.log(pair[0], pair[1]);
          self.fs.copyTpl(
                self.templatePath(['dynamic', pair[0]].join('/')),
                self.destinationPath([pair[1], pair[2] != undefined ? pair[2] : pair[0]].join('/')), {
                    app_id: app_id
                }
            );
        });
      }.bind(this));
    }
});
