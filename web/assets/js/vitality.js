/*!
 * Vitality v1.3.5 (http://themes.startbootstrap.com/vitality-v1.3.5)
 * Copyright 2013-2015 Start Bootstrap Themes
 * To use this theme you must have a license purchased at WrapBootstrap (https://wrapbootstrap.com)
 */

(function($) {
    "use strict"; // Start of use strict

    // Smooth Scrolling: Smooth scrolls to an ID on the current page.
    // To use this feature, add a link on your page that links to an ID, and add the .page-scroll class to the link itself. See the docs for more details.
    $('a.page-scroll').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($anchor.attr('href')).offset().top - 50)
        }, 2500, 'easeInOutExpo');
        event.preventDefault();
    });

    // Activates floating label headings for the contact form.
    $("body").on("input propertychange", ".floating-label-form-group", function(e) {
        $(this).toggleClass("floating-label-form-group-with-value", !!$(e.target).val());
    }).on("focus", ".floating-label-form-group", function() {
        $(this).addClass("floating-label-form-group-with-focus");
    }).on("blur", ".floating-label-form-group", function() {
        $(this).removeClass("floating-label-form-group-with-focus");
    });

    // Closes the Responsive Menu on Menu Item Click
    $('.navbar-collapse ul li a').click(function() {
        $('.navbar-toggle:visible').click();
    });

    // Owl Carousel Settings
    $(".about-carousel").owlCarousel({
        items: 3,
        navigation: true,
        pagination: false,
        navigationText: [
            "<i class='fa fa-angle-left'></i>",
            "<i class='fa fa-angle-right'></i>"
        ],
    });

    $(".portfolio-carousel").owlCarousel({
        singleItem: true,
        navigation: true,
        pagination: false,
        navigationText: [
            "<i class='fa fa-angle-left'></i>",
            "<i class='fa fa-angle-right'></i>"
        ],
        autoHeight: true,
        mouseDrag: false,
        touchDrag: false,
        transitionStyle: "fadeUp"
    });

    $(".testimonials-carousel").owlCarousel({
        singleItem: true,
        navigation: true,
        pagination: true,
        autoHeight: true,
        navigationText: [
            "<i class='fa fa-angle-left'></i>",
            "<i class='fa fa-angle-right'></i>"
        ],
        transitionStyle: "backSlide"
    });

    $(".portfolio-gallery").owlCarousel({
        items: 3,
    });

    // Magnific Popup jQuery Lightbox Gallery Settings
    $('.gallery-link').magnificPopup({
        type: 'image',
        gallery: {
            enabled: true
        },
        image: {
            titleSrc: 'title'
        }
    });

    // Formstone Background - Video Background Settings
    $("header.video").background({
        source: {
            poster: "assets/img/bg-mobile-fallback.jpg",
            mp4: "assets/mp4/camera.mp4"
        }
    });

    // Scrollspy: Highlights the navigation menu items while scrolling.
    $('body').scrollspy({
        target: '.navbar-fixed-top',
        offset: 51
    });
    
    //credit pagina
    $("#payment_button").click(function(){
        console.log("click!");
        $("#payment_choices").toggle();
    });
	
	// Add an event that triggers when the submit
	// button is pressed.
    $("#mc-embedded-subscribe").click(function() {
    	
    	// Get the text from the two inputs.
    	var email = $("#mce-EMAIL").val();
    	
    	// Fail if one of the two inputs is empty, as we need
    	// both a band name and albums to make an insertion.
    	if (email === "") {
    		return;
    	}
    	
    	// Ajax POST request, similar to the GET request.
    	$.post('emailservlet',{"email": email},
            function() { // on success
                $("#mce-EMAIL").val("");
                $("#mce-success-response").append("Uw e-mail werd geregistreerd.");
            })
            .fail(function() { //on failure
                alert("Insertion failed.");
            });
    });
    
    //form validation
    $('#destinationlastname').on('focusout', function() {
	    var input=$(this);
	    var is_name=input.val();
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
    });
    
    $('#destinationstreetname').on('focusout', function() {
	    var input=$(this);
	    var is_name=input.val();
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
    });
    
    $('#destinationstreetnumber').on('focusout', function() {
	    var input=$(this);
	    var is_name=input.val();
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
    });

    $('#destinationzipcode').on('focusout', function() {
	    var input=$(this);
	    var is_name=input.val();
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
    });
    
    $('#destinationcity').on('focusout', function() {
	    var input=$(this);
	    var is_name=input.val();
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
    });
    
    $('#destinationemail').on('focusout', function() {
    	var input=$(this);
    	var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    	var is_email=re.test(input.val())||input.val()=="";
    	if(is_email){input.removeClass("invalid").addClass("valid");}
    	else{input.removeClass("valid").addClass("invalid");}
    });
    
    $('#senderlastname').on('focusout', function() {
	    var input=$(this);
	    var is_name=input.val();
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
    });
    
    $('#senderstreetname').on('focusout', function() {
	    var input=$(this);
	    var is_name=input.val();
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
    });
    
    $('#senderstreetnumber').on('focusout', function() {
	    var input=$(this);
	    var is_name=input.val();
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
    });

    $('#senderzipcode').on('focusout', function() {
	    var input=$(this);
	    var is_name=input.val();
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
    });
    
    $('#sendercity').on('focusout', function() {
	    var input=$(this);
	    var is_name=input.val();
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
    });
    
    
    //async notification on button click
    $("#payment_new").click(function(){
        $('#success_upload').html("<div class='alert alert-success'>");
        $('#success_upload > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;").append("</button>");
        $('#success_upload > .alert-success').append("<img height='20px' width='20px' src='assets/img/loader.gif'/><strong> Even geduld, uw brief wordt opgeladen. Zo meteen wordt u doorgestuurd naar de betalingspagina.</strong>");
        $('#success_upload > .alert-success').append('</div>');
    });
    
    $("#upload_new").click(function(){
        var form_data=$("#upload").serializeArray();
    	var error_free=true;
    	for (var input in form_data){
    	    console.log(form_data[input]['name']);
    	    if(form_data[input]['name']){
    		    var element=$("#"+form_data[input]['name']);
    		    var valid=element.hasClass("valid")||element==$("#");
    		    var error_element=$("span", element.parent());
    		    if (!valid){error_element.removeClass("error").addClass("error_show"); error_free=false;console.log('error');}
    		    else{error_element.removeClass("error_show").addClass("error");}
    	    }
    	}
    	if (!error_free){
    		event.preventDefault();
    		$('#success_upload').html("<div class='alert alert-danger'>");
            $('#success_upload > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;").append("</button>");
            $('#success_upload > .alert-danger').append("<strong>Er ontbreken gegevens, kijk even na aub.</strong>");
            $('#success_upload > .alert-danger').append('</div>');
    	}
    	else{
        	$('#success_upload').html("<div class='alert alert-success'>");
            $('#success_upload > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;").append("</button>");
            $('#success_upload > .alert-success').append("<img height='20px' width='20px' src='assets/img/loader.gif'/><strong> Even geduld, uw brief wordt opgeladen.</strong>");
            $('#success_upload > .alert-success').append('</div>');
    	}

    });
    
    $("#payment_test").click(function(){
        $('#success_test').html("<div class='alert alert-success'>");
        $('#success_test > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;").append("</button>");
        $('#success_test > .alert-success').append("<img height='20px' width='20px' src='assets/img/loader.gif'/><strong> Even geduld, ww brief wordt opgeladen.</strong>");
        $('#success_test > .alert-success').append('</div>');
    });

    
    // Portfolio Filtering Scripts & Hover Effect
    var filterList = {
        init: function() {

            // MixItUp plugin
            // http://mixitup.io
            $('#portfoliolist').mixitup({
                targetSelector: '.portfolio',
                filterSelector: '.filter',
                effects: ['fade'],
                easing: 'snap',
                // call the hover effect
                onMixEnd: filterList.hoverEffect()
            });

        },

        hoverEffect: function() {

            // Simple parallax effect
            $('#portfoliolist .portfolio').hover(
                function() {
                    $(this).find('.caption').stop().animate({
                        bottom: 0
                    }, 200, 'easeOutQuad');
                    $(this).find('img').stop().animate({
                        top: -20
                    }, 300, 'easeOutQuad');
                },
                function() {
                    $(this).find('.caption').stop().animate({
                        bottom: -75
                    }, 200, 'easeInQuad');
                    $(this).find('img').stop().animate({
                        top: 0
                    }, 300, 'easeOutQuad');
                }
            );

        }

    };

    filterList.init();

})(jQuery); // End of use strict

// Load WOW.js on non-touch devices
var isPhoneDevice = "ontouchstart" in document.documentElement;
$(document).ready(function() {
    if (isPhoneDevice) {
        //mobile
    } else {
        //desktop               
        // Initialize WOW.js
        wow = new WOW({
            offset: 50
        })
        wow.init();
    }
});