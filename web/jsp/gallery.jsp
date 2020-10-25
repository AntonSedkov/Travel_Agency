<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<body>

<section class="our_gallery section_padding">
    <div class="container">

        <div class="row justify-content-center">
            <div class="col-xl-6">
                <div class="section_tittle text-center">
                    <h2><fmt:message key="label.gallery"/></h2>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="card-columns">

                    <div class="card">
                        <a href="${pageContext.request.contextPath}/pics/gallery/gallery_1.jpg" class="gallery_img">
                            <img src="${pageContext.request.contextPath}/pics/gallery/gallery_1.jpg"
                                 class="card-img-top" alt="Gallery picture one">
                        </a>
                    </div>

                    <div class="card">
                        <a href="${pageContext.request.contextPath}/pics/gallery/gallery_3.jpg" class="gallery_img">
                            <img src="${pageContext.request.contextPath}/pics/gallery/gallery_3.jpg"
                                 class="card-img-top" alt="Gallery picture two">
                        </a>
                    </div>

                    <div class="card">
                        <a href="${pageContext.request.contextPath}/pics/gallery/gallery_2.jpg" class="gallery_img">
                            <img src="${pageContext.request.contextPath}/pics/gallery/gallery_2.jpg"
                                 class="card-img-top" alt="Gallery picture three">
                        </a>
                    </div>

                    <div class="card">
                        <a href="${pageContext.request.contextPath}/pics/gallery/gallery_4.jpg" class="gallery_img">
                            <img src="${pageContext.request.contextPath}/pics/gallery/gallery_4.jpg"
                                 class="card-img-top" alt="Gallery picture four">
                        </a>
                    </div>

                    <div class="card">
                        <a href="${pageContext.request.contextPath}/pics/gallery/gallery_5.jpg" class="gallery_img">
                            <img src="${pageContext.request.contextPath}/pics/gallery/gallery_5.jpg"
                                 class="card-img-top" alt="Gallery picture five">
                        </a>
                    </div>

                </div>
            </div>
        </div>

    </div>
</section>

</body>