require('date-utils');
var sys=require('sys');
var mysql = require('mysql');
var dbName = 'PushServer';
var client = mysql.createConnection({
        hostname: 'localhost',
        user: 'username',
        password: 'password'
});

client.connect(function(error, result){
        if(error){
                return;
        }
        logger('connected to mySql');
        connectionDB();
});

function connectionDB(){
        logger('Trying to connect to DB');
        client.query('USE '+dbName,function(error){
                if(error){
                        var string="";
                        string+=error;
                        var has = string.indexOf('ER_BAD_DB_ERROR: Unknown database \''+dbName+'\'');
                        if(has>0){
                                logger('Creating Database...');
                                client.query('CREATE DATABASE '+dbName);
                                connectionDB();
                        }
                }
                else{
                        logger('Creating Table...');
                        client.query('create table regId( id INT NOT NULL AUTO_INCREMENT, senderId TEXT NOT NULL, regId TEXT NOT NULL, PRIMARY KEY (id));',function(error){
                                if(error)
                                        return
                        });
                        logger('DB is Ready for use.');
                }
        });
}

exports.regist = function (req, res) {
        logger('Registering...');
        var body='';
        req.on('data', function(chunk){
                body+=chunk;
        });
        req.on('end', function(){
                var json = JSON.parse(body);
                var regId = json.regId;
                var senderId = json.senderId;

                client.query('insert into regId(regId,senderId) values("'+regId+'", "'+senderId+'");',function(err,rows){
                        if(err){
                                var e=err;
                                logger('Insert Error : \n',e);
                        }
                        else{
                                logger('Insert Complete');
                        }
                });
        });

        res.end();
}

exports.unregist = function (req, res){
        logger('Unregistering...');

        var body='';
        req.on('data', function(chunk){
                body+=chunk;
        });
        req.on('end', function(){
                var json = JSON.parse(body);
                var regId = json.regId;
                var senderId = json.senderId;
                client.query('delete from regId where regId="'+regId+'" AND senderID="'+senderId+'";',function(err, rows){
                        if(err){
                                var e=err;
                                logger('Delete Error : \n',e);
                        }
                        else{
                                logger('Delete Complete');
                        }
                });

        });
        res.end();

}


exports.send_push = function(req, res) {
        logger('Preparing for Send Message...');

        var body='';

        req.on('data', function(chunk){
            body+=chunk;
    });

    req.on('end',function(){
            var json = JSON.parse(body);
            var senderId = json.senderId;
            var gcm=require('node-gcm');
            var key1='keyqwe';

            var message=new gcm.Message();
            message.addData('title','TestMesage');
            message.addData('key3','message2');
            var server_access_key='1번 글에서 확득한 api키';
            var sender=new gcm.Sender(server_access_key);

            var registrationIds=[];
            var rows='';
            client.query('select regId from regId where senderId="' +senderId+ '";',function(err, rows){
                    if(err){
                            var e=err;
                            logger('Could not load regId List : \n',e);
                    }else{
                            for(var i in rows){
                                    var rid=rows[i].regId;
                                    rid = rid.replace(/(\s*)/g,"");
                                    registrationIds.push(rid);
                            }
                            logger('Sendding Messages...');
                            sender.send(message, registrationIds, 4, function (err, result) {
                                    if(err){
                                            var e=err;
                                            logger('Could not Send : \n',e);
                                    } else{
                                            var r=result;
                                            logger('Send result : \n',r);
                                    }
                            });
                            logger('Complete Sending!');
                    }
            });
    });
    res.end();
}
function logger(msg,log){
    var d=new Date();
    d=d.toFormat('MM/D HH24:MI:SS');
    if(typeof log == 'undefined'){
            console.log('['+d+'] ' + msg);
    }
    else{
        console.log('['+d+'] ' + msg, log);
    }
}
