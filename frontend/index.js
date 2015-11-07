/**
 * Created by eblenert on 04.10.2015.
 */

var express = require('express');
var app = express();

app.use('/', express.static(__dirname + '/'));

app.listen(process.env.PORT || 3000);
