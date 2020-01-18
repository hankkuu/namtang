<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Contact</title>
</head>
<body>
	<!-- ================ start banner area ================= -->
	<section class="blog-banner-area" id="contact">
		<div class="container h-100">
			<div class="blog-banner">
				<div class="text-center">
					<h1>Contact Us</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">Contact Us</li>
            </ol>
          </nav>
				</div>
			</div>
    </div>
	</section>
	<!-- ================ end banner area ================= -->

	<!-- ================ contact section start ================= -->
  <section class="section-margin--small">
    <div class="container">
      <div class="d-none d-sm-block mb-5 pb-4">
        <div id="map" style="height: 420px;"></div>
        <script>

          // function initMap() {
          //   var uluru = {lat: -25.363, lng: 131.044};
          //   var grayStyles = [
          //     {
          //       featureType: "all",
          //       stylers: [
          //         { saturation: -90 },
          //         { lightness: 50 }
          //       ]
          //     },
          //     {elementType: 'labels.text.fill', stylers: [{color: '#A3A3A3'}]}
          //   ];
          //   var map = new google.maps.Map(document.getElementById('map'), {
          //     center: {lat: 37.402265, lng: 127.1012932},
          //     zoom: 17,
          //     styles: grayStyles,
          //     scrollwheel:  false
          //   });
          //
          //   var marker = new google.maps.Marker({
          //     position: curLatLng,
          //     map: map,
          //     title: 'Hello World!'
          //   });
          //
          // }


          function initMap() {
            // Initial location of a map
            var curLatLng = {
              lat: 37.402265,
              lng: 127.1012932
            };

            var curLatLngg = {
              lat: 37.40265,
              lng: 127.1012932
            };

            // Creates a map object.
            var map = new google.maps.Map(document.getElementById('map'), {
              center: curLatLng,
              scrollwheel: false,
              zoom: 17
            });

            // Creates a marker on the map.
            var marker = new google.maps.Marker({
              position: curLatLng,
              map: map,
              title: 'Kosta'
            });

            infoWindow = new google.maps.InfoWindow();
            infoWindow.setOptions({
              content: "  한국소프트웨어기술진흥협회 ( Kosta )  ",
              position: curLatLngg,
            });

            infoWindow.open(map);

          }

        </script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCMVUXSBBdRYQgfwMwYTdJFifVUfwvPx9k&callback=initMap"></script>

      </div>


      <div class="row">
        <div class="col-md-4 col-lg-3 mb-4 mb-md-0">
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-home"></i></span>
            <div class="media-body">
              <h3>한국소프트웨어기술진흥협회 (Kosta)</h3>
              <p>성남시 분당구 대왕판교로 670</p>
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-headphone"></i></span>
            <div class="media-body">
              <h3><a href="tel:454545654">031 - 1111 - 2222</a></h3>
              <p>월 ~ 금 9am to 7pm</p>
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-email"></i></span>
            <div class="media-body">
              <h3><a href="mailto:support@colorlib.com">support@book.com</a></h3>
              <p>언제나 친절하게 답변해드립니다.</p>
            </div>
          </div>
        </div>
        <div class="col-md-8 col-lg-9">
          <form action="#/" class="form-contact contact_form" action="contact_process.php" method="post" id="contactForm" novalidate="novalidate">
            <div class="row">
              <div class="col-lg-5">
                <div class="form-group">
                  <input class="form-control" name="name" id="name" type="text" placeholder="Enter your name">
                </div>
                <div class="form-group">
                  <input class="form-control" name="email" id="email" type="email" placeholder="Enter email address">
                </div>
                <div class="form-group">
                  <input class="form-control" name="subject" id="subject" type="text" placeholder="Enter Subject">
                </div>
              </div>
              <div class="col-lg-7">
                <div class="form-group">
                    <textarea class="form-control different-control w-100" name="message" id="message" cols="30" rows="5" placeholder="Enter Message"></textarea>
                </div>
              </div>
            </div>
            <div class="form-group text-center text-md-right mt-3">
              <button type="submit" class="button button--active button-contactForm">Send Message</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
	<!-- ================ contact section end ================= -->

</body>
</html>