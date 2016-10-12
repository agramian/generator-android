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
        // 2d array of file and destination folder pairs
        var dynamic_files = [
          ['AndroidManifest.xml', main_dir],
          ['MainActivity.java', main_src_path],
          ['MainApplication.java', main_src_path],
        ];
        // copy dynamic
        var self = this;
        dynamic_files.forEach(function each(pair) {
          self.log(pair[0], pair[1]);
          self.fs.copyTpl(
                self.templatePath(['dynamic', pair[0]].join('/')),
                self.destinationPath([pair[1], pair[0]].join('/')), {
                    app_id: app_id
                }
            );
        });
      }.bind(this));
    }
});
