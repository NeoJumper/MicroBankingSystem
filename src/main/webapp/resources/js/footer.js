// footer.js 파일

const jQueryScript = document.createElement('script');
jQueryScript.src = 'https://code.jquery.com/jquery-3.6.0.min.js';
document.body.insertAdjacentElement('beforeend', jQueryScript);

// Bootstrap
const bootstrapScript = document.createElement('script');
bootstrapScript.src = 'https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js';
bootstrapScript.integrity = 'sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz';
bootstrapScript.crossOrigin = 'anonymous';
document.body.appendChild(bootstrapScript);

// SweetAlert
const sweetalertScript = document.createElement('script');
sweetalertScript.src = 'https://unpkg.com/sweetalert/dist/sweetalert.min.js';
document.body.appendChild(sweetalertScript);
