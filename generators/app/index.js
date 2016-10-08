var generators = require('yeoman-generator');

module.exports = generators.Base.extend({
  copyTemplate: function () {
     this.fs.copy(
       this.templatePath('**'),
       this.destinationRoot()
     )
     // rename gitignore to .gitignore
     this.fs.move(
       this.destinationPath('gitignore'),
       this.destinationPath('.gitignore')
     )
  }
});
