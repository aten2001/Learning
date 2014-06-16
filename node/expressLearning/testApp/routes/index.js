
/*
 * Root File for routes
 */

exports.homepage = function(req, res){
  res.render('homepage', { title: 'Lungiclads' });
};

