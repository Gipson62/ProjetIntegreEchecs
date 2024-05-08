package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

public class Tag {
    private int tag;

    public Tag(int tag) throws IllegalAccountArgumentException{
        setTag(tag);
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) throws IllegalAccountArgumentException {
        if (tag < 0) {
            throw new IllegalAccountArgumentException("Tag must be positive");
        }
        this.tag = tag;
    }

}