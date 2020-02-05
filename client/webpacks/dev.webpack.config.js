var merge = require('webpack-merge');

var commonConfig = require('./common.webpack.config.js');
var generatedConfig = require('./scalajs.webpack.config.js');

module.exports = merge(generatedConfig, commonConfig);
