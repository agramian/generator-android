var generators = require('yeoman-generator');

module.exports = generators.Base.extend({
  copyTemplate: function () {
     this.fs.copy(
       this.templatePath('*'),
       this.destinationRoot()
     )    
  }
});

