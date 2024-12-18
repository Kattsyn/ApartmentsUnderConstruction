document.querySelectorAll('.carousel-container').forEach(container => {
    const hoverAreas = container.querySelectorAll('.hover-area');
    const images = container.querySelector('.carousel-images');

    hoverAreas.forEach((area, index) => {
        area.addEventListener('mouseenter', () => {
            images.style.transform = `translateX(-${index * 100}%)`;
        });
    });
});

// Обновление минимальной даты окончания строительства
document.getElementById('buildingStartDate').addEventListener('input', function () {
    var startDate = this.value;
    var endDateInput = document.getElementById('plannedBuildingEndDate');
    endDateInput.setAttribute('min', startDate);  // Устанавливаем минимальную дату окончания равной дате начала
    if (endDateInput.value < startDate) {
        endDateInput.value = '';  // Если дата окончания раньше, очищаем поле
    }
});

document.getElementById('plannedBuildingEndDate').addEventListener('input', function () {
    var endDate = this.value;
    var commissioningDate = document.getElementById('commissioningDate');
    commissioningDate.setAttribute('min', endDate);  // Устанавливаем минимальную дату окончания равной дате начала
    if (commissioningDate.value < endDate) {
        commissioningDate.value = '';  // Если дата окончания раньше, очищаем поле
    }
});