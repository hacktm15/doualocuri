var express = require('express');
var app = express();
var mysql = require('mysql');
var bodyParser = require('body-parser');
var connection = mysql.createConnection({
  host     : '172.16.41.129',
  user     : 'root',
  password : 'Passw0rd!',
  database : 'doi_pasi'
});
connection.connect();

var port = process.env.PORT || 8080;

var router = express.Router();
app.use(bodyParser.json());

router.get('/', function(req, res) {
    res.json({ message: 'hooray! welcome to our api!' });   
});

router.route('/pubs')
	 .get(function(req, res) {
	 	connection.query('SELECT * from pubs', function(err, rows, fields) {
	 		res.json({result:rows, success:true});
	 	});
	 	
	 });
router.route('/pubs/:pub_id')
	// + lista zone
    // get the bear with that id (accessed at GET http://localhost:8080/api/bears/:bear_id)
    .get(function(req, res) {
        connection.query('SELECT * from pubs where id = ' + req.params.pub_id, function(err, rows, fields) {
        	connection.query('SELECT * from zones where pub_id = ' + req.params.pub_id, function(err2, rows2, fields2){
        		console.log(rows2);
        		res.json({result:{pub_info: rows[0], zones:rows2}, success: true});	
        	});
        });
    });
router.route('/reservation')
	.post(function(req, res) {
	 	connection.query('INSERT INTO reservation set ?', req.body, function (err, result) {
	 		console.log(result);
	 		console.log(err);
	 		if(!err)
	 			res.json({success: true, reservation_id: result.insertId});
	 		else{
	 			res.json({success: false});
	 		}
	 	});
	});
app.use('/api', router);

app.listen(port);