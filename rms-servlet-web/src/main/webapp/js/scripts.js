// a javascript file
function validate(obj) {
    if (obj.action.value === 'delete') {
        return confirm('Confirm Delete? (Warning : Action Cannot Be Undone)');
    } else {
        return true;
    }
}