var generators = require('yeoman-generator');

module.exports = generators.Base.extend({
  prompting: function () {
      return this.prompt([{
        type    : 'input',
        name    : 'org',
        message : 'Organization name for package com.[org].app',
        default : this.appname
      }, {
        type    : 'input',
        name    : 'appname',
        message : 'Application name for package com.org.[app]'
      }]).then(function (answers) {
        this.log('org', answers.org);
        this.log('app', answers.appname);
        /*
        this.fs.copy(
          this.templatePath('**'),
          this.destinationRoot()
        )
        // rename gitignore to .gitignore
        this.fs.move(
          this.destinationPath('gitignore'),
          this.destinationPath('.gitignore')
        )
        */
      }.bind(this));
    }
});
