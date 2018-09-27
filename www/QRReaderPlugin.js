var exec = require('cordova/exec');

exports.readQR = function (arg0, success, error) {
    exec(success, error, 'QRReaderPlugin', 'readQR', [arg0]);
};
