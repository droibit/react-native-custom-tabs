/**
 * Utility class for opening http or https URL in Custom Tabs.
 */
export class CustomTabs {

  /**
   * Opens the URL on Custom Tab.
   *
   * @param url the Uri to be opened.
   * @param option the Option to customize Custom Tabs of look & feel.
   */
  static openURL(url: string, option: TabOption = {}): Promise<boolean>;
}

/**
 * Options to customize Custom Tabs of look & feel.
 */
export interface TabOption {

  /**
   * the Toolbar color.
   * Supported formats are: #RRGGBB, #AARRGGBB, etc.
   *
   * {@link http://d.android.com/reference/android/graphics/Color.html#parseColor(java.lang.String) Color.parseColor(String)}
   */
  toolbarColor?: string;

  /**
   * Enables the url bar to hide as the user scrolls down on the page.
   */
  enableUrlBarHiding?: boolean;

  /**
   * Sets whether the title should be shown in the custom tab.
   */
  showPageTitle?: boolean;

  /**
   * Whether to add a default shared items of the menu.
   */
  enableDefaultShare?: boolean;

  /**
   * Sets the exit and start animations.
   *
   * Each property needs to be an Andrion animation resource ID,
   * e.g. 'com.github.droibit.android.reactnative.customtabs.example:anim/slide_out_bottom'
   *
   * @see ANIMATIONS_FADE
   * @see ANIMATIONS_SLIDE
   */
  animations?: Animations;

  /**
   * Sets any custom headers that should be used.
   */
  headers?: any;

  /**
   * Workaround that Custom Tabs doesn't close on redirecting back to app scheme.
   */
  forceCloseOnRedirection?: boolean;
}

/**
 * Custom Tabs start and end Animation.
 */
export interface Animations {
  startEnter: string;
  startExit: string;
  endEnter: string;
  endExit: string; 
}

/**
 * Fade in at start, Fade out at exit.
 */
export const ANIMATIONS_FADE: Animations;

/**
 * Slide in from left at start, Slide out to right.at exit.
 */
export const ANIMATIONS_SLIDE: Animations;