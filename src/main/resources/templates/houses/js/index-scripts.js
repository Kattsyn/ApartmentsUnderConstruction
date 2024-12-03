document.querySelectorAll('.carousel-container').forEach(container => {
    const hoverAreas = container.querySelectorAll('.hover-area');
    const images = container.querySelector('.carousel-images');

    hoverAreas.forEach((area, index) => {
        area.addEventListener('mouseenter', () => {
            images.style.transform = `translateX(-${index * 100}%)`;
        });
    });
});
