/**
 * 
 */

function uploadImage() {
	var bar = $('.bar');
	var percent = $('.percent');
	
	var file = document.querySelector('input[type=file]').files[0];
	
	if (file) {
      var reader = new FileReader();
      var encoded;
      reader.readAsDataURL(file);
      reader.onload = function(e) { 
    	  encoded = e.target.result;
    	  var imgEncode;
    	  if (encoded.indexOf("data:image/jpeg;base64,") != -1) {
    		  var imgEncoded = encoded.replace("data:image/jpeg;base64,", "");
    	  }
    	  else if (encoded.indexOf("data:image/jpg;base64,") != -1) {
    		  var imgEncoded = encoded.replace("data:image/jpg;base64,", "");
    	  }
    	  else if (encoded.indexOf("data:image/png;base64,") != -1) {
    		  var imgEncoded = encoded.replace("data:image/png;base64,", "");
    	  }
    	  $.ajax({ 
    		  	xhr: function() {
    			    var xhr = new window.XMLHttpRequest();

    			    xhr.upload.addEventListener("progress", function(evt) {
    			      if (evt.lengthComputable) {
    			        var percentComplete = evt.loaded / evt.total;
    			        percentComplete = parseInt(percentComplete * 100);
    			        var percentVal = percentComplete + '%';
        		        bar.width(percentVal);
        		        percent.html(percentVal);
    			        //console.log(percentComplete);
    			        if (percentComplete === 100) {
    			        	var percentVal = '100%';
    	    		        bar.width(percentVal);
    	    		        percent.html(percentVal);
    			        }

    			      }
    			    }, false);

    			    return xhr;
    			  },
    		    url: 'https://api.imgur.com/3/image',
    		    headers: {
    		        'Authorization': 'Client-ID 9a8594613d16dd6'
    		    },
    		    type: 'POST',
    		    data: {
    		        'image': imgEncoded
    		    },
    		    success: function(data) { 
    		    	$('#ImageRef').val(data.data.link);
    		    	$('#ImageWidth').val(data.data.width);
    		    	$('#ImageHeight').val(data.data.height);
    		    	document.getElementById("ImageRefValue").innerHTML = data.data.link;
    		    	console.log(data.data); 
    		    }
    		});
	  }
	} 
}

function doUploadAd() {
	
}